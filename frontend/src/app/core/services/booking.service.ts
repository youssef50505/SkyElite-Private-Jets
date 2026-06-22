import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

export interface CreateBookingRequest {
  flightId: string;
  passengerId: string;
  agentId?: string;
  specialRequests?: string;
}

export interface BookingResponse {
  id: string;
  bookingReference: string;
  bookingDate: string;
  status: string;
  totalAmount: number;
}

@Injectable({ providedIn: 'root' })
export class BookingService {
  private readonly API_URL = 'http://localhost:8080/api/bookings';
  constructor(private http: HttpClient) {}

  createBooking(request: CreateBookingRequest): Observable<BookingResponse> {
    return this.http.post<BookingResponse>(`${this.API_URL}/charter`, request);
  }
  
  getBooking(id: string): Observable<BookingResponse> {
    return this.http.get<BookingResponse>(`${this.API_URL}/${id}`);
  }
}
