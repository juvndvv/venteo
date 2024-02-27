import { Component, OnInit, inject } from '@angular/core';
import {
  FormControl,
  FormGroup,
  FormsModule,
  ReactiveFormsModule,
} from '@angular/forms';
import { MatTabsModule } from '@angular/material/tabs';
import { PromotionsService } from '../../services/promotions.service';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInput } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { UserPromotion, User } from '../../../types';
import { CookieService } from 'ngx-cookie-service';
import { UserService } from '../../services/user.service';
import { UserSignalService } from '../../services/user-signal.service';
import swall from '../../lib/swall';;

@Component({
  selector: 'app-wallet',
  standalone: true,
  imports: [
    MatTabsModule,
    MatButtonModule,
    MatInput,
    MatFormFieldModule,
    ReactiveFormsModule,
  ],
  templateUrl: './wallet.component.html',
  styleUrl: './wallet.component.css',
})
export class WalletComponent {
  cookieService: CookieService = inject(CookieService);
  userService: UserService = inject(UserService);
  promotionService: PromotionsService = inject(PromotionsService);

  user: User = JSON.parse(this.cookieService.get('user'));
  userPromotionForm = new FormGroup({
    codigo: new FormControl(''),
    userId: new FormControl(''),
  });
  retirarForm = new FormGroup({
    amount: new FormControl(''),
  });

  isUsed = false;
  lastBidMessage: string = '';
  amount:number = 0;

  userSignal = inject(UserSignalService);

  onIngreso() {
    const formValue = this.userPromotionForm.value;

    const userPromotionForm: UserPromotion = {
      code: formValue.codigo || '',
      userId: this.user.id || 0,
    };

    this.promotionService.use(userPromotionForm).subscribe((result) => {
      console.log(result);
      if (result) {
        this.userService.findById(this.user.id || 0).subscribe((user) => {
          this.user = user;
          console.log(user);
          swall('Promoción utilizada', 'success');
          this.cookieService.set('user', JSON.stringify(user));
          this.userSignal.updateUser({
            ...this.user,
            money: user.money,
          });
        });
      }
    });
  }
  onRetirada(){
    const formValue = this.retirarForm.value;
    this.amount = parseInt(formValue.amount||'0');
    console.log('Amount: ', this.amount);
    this.user.money = (this.user.money||0)-this.amount;
    console.log('User money: ', this.user.money);

    if (this.isRetiradaValid(this.amount)) {
      console.log('La cantidad es válida');

      this.userService.patch({money: this.user.money}, this.user.id||0).subscribe((user) => {
        this.cookieService.set('user', JSON.stringify(user));
        this.userSignal.updateUser(user);
        swall('Dinero retirado', 'success');
      });
    }else{
      console.log('La puja no es válida');
    }
    }

    private validateNumber(amount: number): boolean {
      if (isNaN(amount)) {
        this.lastBidMessage = 'La cantidad debe ser un número';
        console.log('La cantidad debe ser un número');
        return false;
      }
      return true;
    }

    private isRetiradaValid(amount: number): boolean {
      if (!this.validateNumber(amount)) return false;
      return true;
    }
}
