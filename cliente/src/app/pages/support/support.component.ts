import { Component, OnInit, inject } from '@angular/core';
import { IssuesService } from '../../services/issues.service';
import { Issue, User } from '../../../types';
import { FormBuilder, FormControl, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { Router, RouterLink, RouterLinkActive } from '@angular/router';
import { MatTabsModule } from '@angular/material/tabs';
import { MatButtonModule } from '@angular/material/button';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInput } from '@angular/material/input';
import { UserService } from '../../services/user.service';
import { CookieService } from 'ngx-cookie-service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-support',
  standalone: true,
  imports: [
    FormsModule,
    ReactiveFormsModule, 
    RouterLink,
    RouterLinkActive,
    MatTabsModule,
    MatFormFieldModule,
    MatInput,
    MatButtonModule,
    ReactiveFormsModule,
    FormsModule,
  ],
  templateUrl: './support.component.html',
  styleUrl: './support.component.css'
})

export class SupportComponent{

  issueService: IssuesService = inject(IssuesService);
  cookieService: CookieService = inject(CookieService);
  userService: UserService = inject(UserService);
  user : User = JSON.parse(this.cookieService.get('user'));

  supportForm = new FormGroup({
    subject: new FormControl('', [Validators.required, Validators.minLength(3), Validators.maxLength(50)]),
    message: new FormControl('', [Validators.required, Validators.minLength(3), Validators.maxLength(255)])
  })

  hasSupportError = false;

  constructor(private router: Router) {}

  onSave(){

    if (this.supportForm.invalid) {
      return;
    }

    const formValue = this.supportForm.value;
    const userId = this.user.id || 0;

    const supportForm: Issue = {
      subject: formValue.subject || '',
      message: formValue.message || '',
      createdAt: new Date(),
      isSolved: false,
      userId: userId
    }

    console.log(supportForm);

    this.issueService.saveIssue(supportForm).subscribe(
      (response) => {
        console.log(response);
        const Toast = Swal.mixin({
          toast: true,
          position: "top",
          showConfirmButton: false,
          timer: 3000,
          timerProgressBar: true,
          didOpen: (toast) => {
            toast.onmouseenter = Swal.stopTimer;
            toast.onmouseleave = Swal.resumeTimer;
          }
        });
        Toast.fire({
          icon: "success",
          title: "¡Mensaje enviado!"
        });
        
      },
      (error) => {
        console.log(error);
        this.hasSupportError = true;
        Swal.fire({
          title: 'Error',
          text: 'Hubo un error al enviar el formulario. Por favor, inténtalo de nuevo más tarde.',
          icon: 'error',
          confirmButtonText: 'OK'
        });
      }
    );
    
  }

  getSubjectError() {

    if (this.supportForm.get('subject')?.hasError('required')) {
      return 'Debes introducir un asunto';
    }

    if (this.supportForm.get('subject')?.hasError('minlength')) {
      return 'El asunto debe de tener al menos 3 caracteres';
    }

    if (this.supportForm.get('subject')?.hasError('maxlength')) {
      return 'El asunto debe de tener como máximo 50 caracteres';
    }

    return '';
  }

  getMessageError() {

    if (this.supportForm.get('message')?.hasError('required')) {
      return 'Debes introducir un mensaje';
    }

    if (this.supportForm.get('message')?.hasError('minlength')) {
      return 'El mensaje debe de tener al menos 3 caracteres';
    }

    if (this.supportForm.get('message')?.hasError('maxlength')) {
      return 'El mensaje debe de tener como máximo 255 caracteres';
    }

    return '';

  }

}