import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AnalyticsService {
  private apiUrl = 'http://localhost:8080/api/v1/analytics';

  constructor(private http: HttpClient) {}

  getOperationsCommandMetrics(): Observable<any> {
    return this.http.get<any>(`${this.apiUrl}/operations-command`);
  }

  getRevenueManagementMetrics(): Observable<any> {
    return this.http.get<any>(`${this.apiUrl}/revenue-management`);
  }
}
