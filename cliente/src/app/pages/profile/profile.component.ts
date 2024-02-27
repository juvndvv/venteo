import { Component, inject } from '@angular/core';
import { User } from '../../../types';
import {
  FormControl,
  FormGroup,
  FormsModule,
  ReactiveFormsModule,
} from '@angular/forms';
import { CookieService } from 'ngx-cookie-service';
import { RouterLink, RouterLinkActive } from '@angular/router';
import { MatTabsModule } from '@angular/material/tabs';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInput } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { UserService } from '../../services/user.service';
import { HeaderComponent } from '../../components/header/header.component';
import { getImageUrl, uploadImage } from '../../lib/imageUrl';
import { Validators } from '@angular/forms';
import swall from '../../lib/swall';
import { get } from 'http';

@Component({
  selector: 'app-profile',
  standalone: true,
  imports: [
    ReactiveFormsModule,
    RouterLink,
    RouterLinkActive,
    MatTabsModule,
    MatFormFieldModule,
    MatInput,
    MatButtonModule,
    ReactiveFormsModule,
    FormsModule,
    HeaderComponent,
  ],
  providers: [UserService, CookieService],
  templateUrl: './profile.component.html',
  styleUrl: './profile.component.css',
})
export class ProfileComponent {
  cookieService: CookieService = inject(CookieService);
  userService: UserService = inject(UserService);
  user: User = JSON.parse(this.cookieService.get('user'));
  imageUrl = getImageUrl(this.user.imageUrl, 200);

  editProfile = new FormGroup({
    firstName: new FormControl(this.user.firstName),
    lastName: new FormControl(this.user.lastName),
    userName: new FormControl(this.user.userName),
    email: new FormControl(this.user.email),
    password: new FormControl(this.user.password, Validators.required),
    repeatPassword: new FormControl(this.user.repeatPassword),
    imageUrl: new FormControl(this.user.imageUrl),
  });

  onEdit() {
    const formValue = this.editProfile.value;

    const fileName = formValue.imageUrl?.replace(/^.*[\\\/]/, '').split('.')[0];

    const userForm: User = {
      firstName: formValue.firstName || '',
      lastName: formValue.lastName || '',
      userName: formValue.userName || '',
      password: formValue.password || '',
      email: formValue.email || '',
      imageUrl: fileName || '',
    };

    const userId = this.user.id || 0;
    console.log(userForm, userId);
    this.userService.update(userForm, userId).subscribe((user: User) => {
      this.cookieService.set('user', JSON.stringify(user));
      this.user = user;
    });

    if (this.imageFile) {
      uploadImage(this.imageFile || new File([''], ''))
        .then((url: string) => {
          userForm.imageUrl = url;
          console.log(userForm);
        })
        .then(() => {
          this.userService.update(userForm, this.user.id || 0).subscribe(
            (user: User) => {
              this.cookieService.set('user', JSON.stringify(user));
            },
            (error) => {
              console.log('error', error);
            }
          );
        });
    }
    swall('Perfil actualizado', 'success');
  }

  imageFile: File | null = null;

  onFileSelected(event: any) {
    console.log(1);
    if (event.target.files.length > 0) {
      console.log(2);
      const file = event.target.files[0];
      this.imageFile = file;
      console.log(this.imageFile);
      uploadImage(this.imageFile || new File([''], '')).then((url: string) => {
        this.imageUrl = getImageUrl(url, 200);
      });
    }
  }

  getErrorMessage(): string {
    if (this.editProfile.controls.password.hasError('required')) {
      return 'Contraseña obligatoria';
    }
    return '';
  }
}
