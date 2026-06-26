import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class FleetService {
  private apiUrl = 'http://localhost:8080/api/v1/aircrafts';

  constructor(private http: HttpClient) {}

  getAllAircrafts(): Observable<any[]> {
    return this.http.get<any[]>(this.apiUrl);
  }

  addAircraft(aircraftData: any): Observable<any> {
    return this.http.post<any>(this.apiUrl, aircraftData);
  }

  updateAircraftStatus(id: string, status: string): Observable<void> {
    let params = new HttpParams().set('status', status);
    return this.http.patch<void>(`${this.apiUrl}/${id}/status`, null, { params });
  }
}
