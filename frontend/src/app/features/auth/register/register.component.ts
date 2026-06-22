import { Component, inject, signal } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Router, RouterModule } from '@angular/router';
import { AuthService } from '../../../core/services/auth.service';

@Component({
  selector: 'app-register',
  standalone: true,
  imports: [CommonModule, RouterModule],
  templateUrl: './register.component.html',
  styleUrl: './register.component.css'
})
export class RegisterComponent {
  private authService = inject(AuthService);
  private router = inject(Router);

  isLoading = signal(false);
  errorMessage = signal<string | null>(null);

  onSubmit(event: Event) {
    event.preventDefault();
    const form = event.target as HTMLFormElement;
    const firstName = (form.querySelector('input[placeholder="First"]') as HTMLInputElement).value;
    const lastName = (form.querySelector('input[placeholder="Last"]') as HTMLInputElement).value;
    const email = (form.querySelector('input[type="email"]') as HTMLInputElement).value;
    const password = (form.querySelector('input[type="password"]') as HTMLInputElement).value;
    const phoneNumber = (form.querySelector('input[type="tel"]') as HTMLInputElement).value || undefined;

    this.isLoading.set(true);
    this.errorMessage.set(null);

    this.authService.register({ firstName, lastName, email, password, phoneNumber, role: 'PASSENGER' }).subscribe({
      next: () => {
        this.isLoading.set(false);
        this.router.navigate(['/login']);
      },
      error: (err: any) => {
        this.isLoading.set(false);
        this.errorMessage.set(err.error?.detail || 'Failed to register. Please try again.');
      }
    });
  }
}
