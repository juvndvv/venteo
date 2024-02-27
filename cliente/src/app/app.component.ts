import { CommonModule } from '@angular/common';
import { Component, OnInit, inject } from '@angular/core';
import {
  Router,
  RouterLink,
  RouterLinkActive,
  RouterOutlet,
} from '@angular/router';
import { Link, User } from '../types';
import { HeaderComponent } from './components/header/header.component';
import { FooterComponent } from './components/footer/footer.component';
import { CookieService } from 'ngx-cookie-service';
import { LoginComponent } from './pages/login/login.component';
import { UserSignalService } from './services/user-signal.service';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [
    HeaderComponent,
    FooterComponent,
    CommonModule,
    RouterLink,
    RouterLinkActive,
    RouterOutlet,
    LoginComponent,
  ],
  providers: [CookieService],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css',
})
export class AppComponent implements OnInit {
  user: User | null = null;

  links: Link[] = [
    { path: '/', text: 'Home' },
    { path: '/profile', text: 'Profile' },
    { path: '/notifications', text: 'Notifications' },
  ];

  constructor(
    private cookieService: CookieService,
    private router: Router,
    private userSignalService: UserSignalService
  ) {}

  ngOnInit() {
    this.user = this.readUserCookie();
    this.userSignalService.updateUser(this.user || ({} as User));
  }

  /**
   * Obtiene el usuario almacenado en la cookie del navegador.
   */
  readUserCookie() {
    console.log('readUserCookie');
    const cookie = this.cookieService.get('user');

    if (cookie) {
      const cookieValue = JSON.parse(cookie);
      return cookieValue;
    } else {
      this.router.navigate(['/login']);
    }
  }
}
