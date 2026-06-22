import { Routes } from '@angular/router';
import { HomeComponent } from './features/home/home/home.component';
import { authGuard } from './core/guards/auth.guard';

export const routes: Routes = [
  { path: '', component: HomeComponent },
  { path: 'fleet', loadComponent: () => import('./features/fleet/fleet/fleet.component').then(m => m.FleetComponent) },
  { path: 'booking', loadComponent: () => import('./features/booking/booking-wizard/booking-wizard.component').then(m => m.BookingWizardComponent) },
  { path: 'login', loadComponent: () => import('./features/auth/login/login.component').then(m => m.LoginComponent) },
  { path: 'register', loadComponent: () => import('./features/auth/register/register.component').then(m => m.RegisterComponent) },
  { path: 'dashboard', loadComponent: () => import('./features/dashboard/dashboard/dashboard.component').then(m => m.DashboardComponent), canActivate: [authGuard] },
];
