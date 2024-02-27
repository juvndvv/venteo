import { Injectable, effect, inject, signal } from '@angular/core';
import { User } from '../../types';
import { CookieService } from 'ngx-cookie-service';

@Injectable({
  providedIn: 'root',
})
export class UserSignalService {
  cookieService: CookieService = inject(CookieService);

  private userSignal = signal({} as User);
  readonly user = this.userSignal.asReadonly();

  constructor() {
    effect(() => {
      if (this.userSignal().id) {
        this.cookieService.set('user', JSON.stringify(this.userSignal()));
      }
    });
  }

  updateUser(user: User) {
    this.userSignal.set(user);
  }

  clearUser() {
    this.userSignal.set({} as User);
  }
}
