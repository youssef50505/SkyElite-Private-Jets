import { Component } from '@angular/core';
import { RouterLink } from '@angular/router';
import { CommonModule } from '@angular/common';
import { AuthService } from '../../../core/services/auth.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-navbar',
  imports: [RouterLink, CommonModule],
  templateUrl: './navbar.html',
  styleUrl: './navbar.css'
})
export class Navbar {
  isDarkMode = false;

  constructor(
    public authService: AuthService,
    private router: Router
  ) {
    if (typeof document !== 'undefined') {
      this.isDarkMode = document.documentElement.classList.contains('dark');
    }
  }

  logout() {
    this.authService.logout();
    this.router.navigate(['/']);
  }

  toggleDarkMode() {
    if (typeof document === 'undefined') return;
    this.isDarkMode = !this.isDarkMode;
    if (this.isDarkMode) {
      document.documentElement.classList.add('dark');
    } else {
      document.documentElement.classList.remove('dark');
    }
  }
}
