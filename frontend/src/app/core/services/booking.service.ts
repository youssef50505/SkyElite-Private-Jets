import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class BookingService {
  private apiUrl = 'http://localhost:8080/api/v1/bookings';

  constructor(private http: HttpClient) {}

  createBooking(bookingData: any): Observable<any> {
    return this.http.post<any>(this.apiUrl, bookingData);
  }

  getMyBookings(): Observable<any[]> {
    return this.http.get<any[]>(`${this.apiUrl}/me`);
  }

  getAllBookings(): Observable<any[]> {
    return this.http.get<any[]>(this.apiUrl);
  }

  updateBookingStatus(reference: string, status: string): Observable<void> {
    let params = new HttpParams().set('status', status);
    return this.http.patch<void>(`${this.apiUrl}/${reference}/status`, null, { params });
  }
}
