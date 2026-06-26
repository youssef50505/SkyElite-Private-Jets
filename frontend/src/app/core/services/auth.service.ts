import { Injectable, signal } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, tap } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private apiUrl = '/api/v1/auth';
  
  private tokenSignal = signal<string | null>(localStorage.getItem('token'));
  private roleSignal = signal<string | null>(localStorage.getItem('role'));
  private emailSignal = signal<string | null>(localStorage.getItem('email'));

  constructor(private http: HttpClient) {}

  login(credentials: any): Observable<any> {
    return this.http.post(`${this.apiUrl}/login`, credentials).pipe(
      tap((response: any) => {
        this.setSession(response.token, response.role, response.email);
      })
    );
  }

  register(userData: any): Observable<any> {
    return this.http.post(`${this.apiUrl}/register`, userData).pipe(
      tap((response: any) => {
        this.setSession(response.token, response.role, response.email);
      })
    );
  }

  private setSession(token: string, role: string, email: string) {
    localStorage.setItem('token', token);
    localStorage.setItem('role', role);
    if (email) localStorage.setItem('email', email);
    this.tokenSignal.set(token);
    this.roleSignal.set(role);
    this.emailSignal.set(email);
  }

  logout() {
    localStorage.removeItem('token');
    localStorage.removeItem('role');
    localStorage.removeItem('email');
    this.tokenSignal.set(null);
    this.roleSignal.set(null);
    this.emailSignal.set(null);
  }

  getCurrentUser(): any {
    const email = this.emailSignal();
    return email ? { email } : null;
  }

  getToken(): string | null {
    return this.tokenSignal();
  }

  getUserRole(): string | null {
    return this.roleSignal();
  }

  isAuthenticated(): boolean {
    return !!this.tokenSignal();
  }
}
