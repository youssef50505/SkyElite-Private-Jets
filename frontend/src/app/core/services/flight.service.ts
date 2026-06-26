import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class FlightService {
  private apiUrl = 'http://localhost:8080/api/v1/flights';

  constructor(private http: HttpClient) {}

  getAllFlights(): Observable<any[]> {
    return this.http.get<any[]>(this.apiUrl);
  }

  getFlightById(id: string): Observable<any> {
    return this.http.get<any>(`${this.apiUrl}/${id}`);
  }

  searchFlights(origin: string, destination: string): Observable<any[]> {
    let params = new HttpParams().set('origin', origin).set('destination', destination);
    return this.http.get<any[]>(`${this.apiUrl}/search`, { params });
  }

  getFeaturedFlights(): Observable<any[]> {
    return this.http.get<any[]>(`${this.apiUrl}/featured`);
  }

  scheduleFlight(flightData: any): Observable<any> {
    return this.http.post<any>(this.apiUrl, flightData);
  }

  updateFlightStatus(id: string, status: string): Observable<void> {
    let params = new HttpParams().set('status', status);
    return this.http.patch<void>(`${this.apiUrl}/${id}/status`, null, { params });
  }
}
