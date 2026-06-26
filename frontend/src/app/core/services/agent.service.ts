import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AgentService {
  private apiUrl = '/api/v1/agent';

  constructor(private http: HttpClient) {}

  getClientPortfolio(): Observable<any[]> {
    return this.http.get<any[]>(`${this.apiUrl}/clients`);
  }
}
