import { Component } from '@angular/core';
import { RouterLink, Router } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { AuthService } from '../../../core/services/auth.service';

@Component({
  selector: 'app-login',
  imports: [RouterLink, FormsModule, CommonModule],
  templateUrl: './login.html',
  styleUrls: ['./login.css']
})
export class Login {
  email = '';
  password = '';
  errorMessage = '';

  constructor(private authService: AuthService, private router: Router) {}

  onSubmit() {
    this.errorMessage = '';
    this.authService.login({ email: this.email, password: this.password }).subscribe({
      next: (res) => {
        // Navigate based on role or to home
        const role = this.authService.getUserRole();
        if (role === 'AGENT') this.router.navigate(['/agent-portal']);
        else if (role === 'PASSENGER') this.router.navigate(['/dashboard']);
        else if (role === 'OPERATIONS') this.router.navigate(['/fleet']);
        else if (role === 'ADMIN') this.router.navigate(['/analytics']);
        else this.router.navigate(['/home']);
      },
      error: (err) => {
        this.errorMessage = 'Invalid email or password';
      }
    });
  }
}
