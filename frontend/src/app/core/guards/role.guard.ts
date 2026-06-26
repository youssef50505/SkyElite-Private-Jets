import { inject } from '@angular/core';
import { CanActivateFn, Router } from '@angular/router';
import { AuthService } from '../services/auth.service';

export const roleGuard: CanActivateFn = (route, state) => {
  const authService = inject(AuthService);
  const router = inject(Router);

  const expectedRoles = route.data['roles'] as Array<string>;
  const userRole = authService.getUserRole();

  if (!authService.isAuthenticated()) {
    router.navigate(['/login']);
    return false;
  }

  if (expectedRoles && expectedRoles.length > 0) {
    if (userRole && expectedRoles.includes(userRole)) {
      return true;
    } else {
      router.navigate(['/home']);
      return false;
    }
  }

  return true;
};
