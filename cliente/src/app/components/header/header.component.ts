import { Component, Input, Renderer2, inject } from '@angular/core';
import { Link, User } from '../../../types';
import { IconComponent } from '../icon/icon.component';
import { RouterLink, RouterLinkActive } from '@angular/router';
import { getImageUrl } from '../../lib/imageUrl';
import { CookieService } from 'ngx-cookie-service';
import { UserSignalService } from '../../services/user-signal.service';

@Component({
  selector: 'app-header',
  standalone: true,
  imports: [IconComponent, RouterLink, RouterLinkActive],
  providers: [CookieService],
  templateUrl: './header.component.html',
  styleUrl: './header.component.css',
})
export class HeaderComponent {
  cookieService: CookieService = inject(CookieService);
  renderer: Renderer2 = inject(Renderer2);
  userSignal: UserSignalService = inject(UserSignalService);

  @Input() links: Link[] = [];
  @Input() user: User = {} as User;
  user2: User = this.userSignal.user();
  isNavOpen = false;
  isDropdownOpen = false;
  imageUrl: string = '';

  constructor() {
    this.user = JSON.parse(this.cookieService.get('user'));
    this.imageUrl = getImageUrl(this.user.imageUrl, 90, false);
  }

  ngAfterViewInit() {
    this.renderer.listen('window', 'click', (event) => {
      const dropdown = document.querySelector('#dropdown') as HTMLElement;

      if (
        event.target !== dropdown &&
        event.target !== document.querySelector('img:has(+ #dropdown)') &&
        event.target !== document.querySelector('#dropdown')
      ) {
        if (this.isDropdownOpen) {
          this.toggleDropdown();
        }
      }
    });

    const dropdown = document.querySelector('#dropdown') as HTMLElement;
    this.renderer.listen(dropdown, 'click', (event) => {
      console.log(2);
    });
  }

  toggleDropdown() {
    const dropdown = document.querySelector('#dropdown') as HTMLElement;
    if (!this.isDropdownOpen) {
      dropdown.classList.remove('slide-out-top');
      dropdown.classList.add('slide-in-top');
      dropdown.classList.toggle('hidden');
      this.isDropdownOpen = true;
    } else {
      dropdown.classList.remove('slide-in-top');
      dropdown.classList.add('slide-out-top');
      setTimeout(() => {
        dropdown.classList.toggle('hidden');
      }, 300);
      this.isDropdownOpen = false;
    }
  }

  toggleNav() {
    if (this.isNavOpen) {
      this.closeNav();
    } else {
      this.openNav();
    }
  }

  closeNav() {
    const nav = document.querySelector('nav') as HTMLElement;
    if (this.isNavOpen) {
      document.body.style.overflow = 'auto';
      nav.classList.add('slide-to-left');
      nav.classList.remove('slide-from-left');
      this.isNavOpen = false;
      setTimeout(() => {
        nav.classList.toggle('hidden');
      }, 450);
    }
  }
  openNav() {
    const nav = document.querySelector('nav') as HTMLElement;
    window.scrollTo(0, 0);
    document.body.style.overflow = 'hidden';
    nav.classList.add('slide-from-left');
    nav.classList.remove('slide-to-left');
    this.isNavOpen = true;
    nav.classList.toggle('hidden');
  }

  logout() {
    this.cookieService.delete('user');
    window.location.reload();
  }
}
