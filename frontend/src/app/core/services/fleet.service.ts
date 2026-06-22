import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

export interface AircraftResponse {
  id: string;
  tailNumber: string;
  model: string;
  capacity: number;
  rangeKm: number;
  manufacturingYear: number;
  imageUrl?: string;
  status: string;
}

@Injectable({ providedIn: 'root' })
export class FleetService {
  private readonly API_URL = 'http://localhost:8080/api/fleet';
  constructor(private http: HttpClient) {}
  
  getAllAircraft(): Observable<AircraftResponse[]> {
    return this.http.get<AircraftResponse[]>(`${this.API_URL}/aircraft`);
  }
}
