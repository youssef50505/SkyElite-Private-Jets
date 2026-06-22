import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

export interface FlightResponse {
  id: string;
  departureAirportIata: string;
  arrivalAirportIata: string;
  scheduledDeparture: string;
  scheduledArrival: string;
  status: string;
  aircraftId: string;
  flightType: string;
  flightNumber?: string;
  basePrice?: number;
}

@Injectable({ providedIn: 'root' })
export class FlightService {
  private readonly API_URL = 'http://localhost:8080/api/flights';
  constructor(private http: HttpClient) {}

  searchFlights(departure: string, arrival: string, date: string): Observable<FlightResponse[]> {
    return this.http.get<FlightResponse[]>(`${this.API_URL}/search?departure=${departure}&arrival=${arrival}&date=${date}`);
  }
}
