import { Routes } from '@angular/router';
import { Home } from './features/home/home';
import { Landing } from './features/landing/landing';
import { Login } from './features/auth/login/login';
import { Register } from './features/auth/register/register';
import { Dashboard } from './features/dashboard/dashboard';
import { AgentPortal } from './features/agent-portal/agent-portal';
import { Fleet } from './features/fleet/fleet';
import { FlightSearch } from './features/flight-search/flight-search';
import { FlightDetails } from './features/flight-details/flight-details';
import { authGuard } from './core/guards/auth.guard';
import { roleGuard } from './core/guards/role.guard';

export const routes: Routes = [
  { path: '', component: Landing },
  { path: 'home', component: Home },
  { path: 'login', component: Login },
  { path: 'register', component: Register },
  { path: 'search', component: FlightSearch },
  { path: 'flight/:id', component: FlightDetails },
  { 
    path: 'dashboard', 
    component: Dashboard,
    canActivate: [authGuard, roleGuard],
    data: { roles: ['PASSENGER'] }
  },
  { 
    path: 'agent-portal', 
    component: AgentPortal,
    canActivate: [authGuard, roleGuard],
    data: { roles: ['AGENT'] }
  },
  { 
    path: 'operations', 
    component: Home, // Placeholder
    canActivate: [authGuard, roleGuard],
    data: { roles: ['OPERATIONS', 'ADMIN'] }
  },
  { 
    path: 'fleet', 
    component: Fleet,
    canActivate: [authGuard, roleGuard],
    data: { roles: ['OPERATIONS', 'ADMIN'] }
  },
  { 
    path: 'analytics', 
    component: Home, // Placeholder
    canActivate: [authGuard, roleGuard],
    data: { roles: ['ADMIN'] }
  },
  { path: '**', redirectTo: '' }
];
