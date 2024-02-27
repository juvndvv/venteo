import { Component, OnInit, inject } from '@angular/core';
import { AuthService } from '../../services/auth.service';
import { LoginForm, RegisterForm, User } from '../../../types';
import {
  FormControl,
  FormGroup,
  FormsModule,
  ReactiveFormsModule,
  Validators,
} from '@angular/forms';
import { CookieService } from 'ngx-cookie-service';
import { Router, RouterLink, RouterLinkActive } from '@angular/router';
import { MatTabsModule } from '@angular/material/tabs';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInput } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { UserService } from '../../services/user.service';
import { HeaderComponent } from '../../components/header/header.component';
import { uploadImage } from '../../lib/imageUrl';
import { UserSignalService } from '../../services/user-signal.service';

@Component({
  selector: 'app-login',
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
  providers: [CookieService],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css',
})
export class LoginComponent implements OnInit {
  cookieService: CookieService = inject(CookieService);
  authService: AuthService = inject(AuthService);
  userService: UserService = inject(UserService);

  hide: boolean = true;

  loginForm = new FormGroup({
    username: new FormControl(''),
    password: new FormControl(''),
  });

  registerForm = new FormGroup({
    firstName: new FormControl('', [
      Validators.required,
      Validators.minLength(3),
      Validators.maxLength(50),
    ]),
    lastName: new FormControl('', [
      Validators.required,
      Validators.minLength(3),
      Validators.maxLength(50),
    ]),
    userName: new FormControl('', [
      Validators.required,
      Validators.minLength(3),
      Validators.maxLength(50),
    ]),
    email: new FormControl('', [Validators.required, Validators.email]),
    password: new FormControl('', [
      Validators.required,
      Validators.minLength(8),
      Validators.maxLength(50),
    ]),
    repeatPassword: new FormControl('', [
      Validators.required,
      Validators.minLength(8),
      Validators.maxLength(50),
    ]),
    image: new FormControl('', [Validators.required]),
  });

  imageFile?: File;

  isLoginValid: boolean = false;

  constructor(private router: Router) {}

  userSignalService: UserSignalService = inject(UserSignalService);
  ngOnInit(): void {
    if (this.userSignalService.user().id) {
      this.redirect();
    }
  }

  onFileChange(event: any) {
    if (event?.target.files.length > 0) {
      const file = event?.target.files[0];
      this.imageFile = file;
    }
  }

  getFirstNameErrorMessage() {
    if (this.registerForm.controls.firstName.hasError('required')) {
      return 'Debes introducir un nombre';
    }

    if (this.registerForm.controls.firstName.hasError('maxlength')) {
      return 'El nombre debe tener menos de 50 caracteres';
    }

    return this.registerForm.controls.firstName.hasError('minlength')
      ? 'El nombre debe tener al menos 3 caracteres'
      : '';
  }

  getLastNameErrorMessage() {
    if (this.registerForm.controls.lastName.hasError('required')) {
      return 'Debes introducir un apellido';
    }

    if (this.registerForm.controls.lastName.hasError('maxlength')) {
      return 'El apellido debe tener menos de 50 caracteres';
    }

    return this.registerForm.controls.lastName.hasError('minlength')
      ? 'El apellido debe tener al menos 3 caracteres'
      : '';
  }

  getUserNameErrorMessage() {
    if (this.registerForm.controls.userName.hasError('required')) {
      return 'Debes introducir un nombre de usuario';
    }

    if (this.registerForm.controls.userName.hasError('maxlength')) {
      return 'El nombre de usuario debe tener menos de 50 caracteres';
    }

    return this.registerForm.controls.userName.hasError('minlength')
      ? 'El nombre de usuario debe tener al menos 3 caracteres'
      : '';
  }

  getEmailErrorMessage() {
    if (this.registerForm.controls.email.hasError('required')) {
      return 'Debes introducir un correo electrónico';
    }

    return this.registerForm.controls.email.hasError('email')
      ? 'No es un correo valido'
      : '';
  }

  getPasswordErrorMessage() {
    if (this.registerForm.controls.password.hasError('required')) {
      return 'Debes introducir una contraseña';
    }

    if (this.registerForm.controls.password.hasError('maxlength')) {
      return 'La contraseña debe tener menos de 50 caracteres';
    }

    if (this.registerForm.controls.password.hasError('minlength')) {
      return 'La contraseña debe tener al menos 8 caracteres';
    }

    return '';
  }

  getRepeatPasswordErrorMessage() {
    if (
      this.registerForm.controls.password.value !==
      this.registerForm.controls.repeatPassword.value
    ) {
      return 'Las contraseñas no coinciden';
    }

    if (this.registerForm.controls.repeatPassword.hasError('required')) {
      return 'Debes repetir la contraseña';
    }

    return '';
  }

  hasLoginError: boolean = false;

  getLoginErrorMessage() {
    return 'Usuario o contraseña incorrecto';
  }

  onLogin() {
    const formValue = this.loginForm.value;

    const loginForm: LoginForm = {
      username: formValue.username || '',
      password: formValue.password || '',
    };

    this.authService.authenticate(loginForm).subscribe(
      (user) => {
        this.writeUserCookie(user as User);
        this.userSignalService.updateUser(user as User);
        this.isLoginValid = true;

        setTimeout(() => {
          this.redirect();
        }, 500);
      },
      (error) => {
        this.hasLoginError = true;
      }
    );
  }

  onRegister() {
    const formValue = this.registerForm.value;

    // Nombre de la imagen
    const fileName = formValue.image?.replace(/^.*[\\\/]/, '').split('.')[0];

    const registerForm: RegisterForm = {
      firstName: formValue.firstName || '',
      lastName: formValue.lastName || '',
      userName: formValue.userName || '',
      email: formValue.email || '',
      password: formValue.password || '',
      repeatPassword: formValue.repeatPassword || '',
      imageUrl: fileName || '',
    };

    if (this.imageFile)
      uploadImage(this.imageFile)
        .then((url: string) => {
          registerForm.imageUrl = url;
          console.log(registerForm);
        })
        .then(() => {
          this.userService.create(registerForm).subscribe(
            (user: User) => {
              this.writeUserCookie(user);
              this.userSignalService.updateUser(user);
              this.isLoginValid = true;

              setTimeout(() => {
                this.redirect();
              }, 500);
            },
            (error) => {
              console.log('error', error);
            }
          );
        });
  }

  redirect() {
    this.router.navigateByUrl('/');
    setTimeout(() => {
      window.location.reload();
    }, 100);
  }

  writeUserCookie(user: User) {
    this.cookieService.set('user', JSON.stringify(user));
  }
}
