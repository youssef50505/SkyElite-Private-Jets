import { Component, inject, signal } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { FlightService, FlightResponse } from '../../../core/services/flight.service';
import { BookingService } from '../../../core/services/booking.service';
import { AuthService } from '../../../core/services/auth.service';

@Component({
  selector: 'app-booking-wizard',
  standalone: true,
  imports: [CommonModule, RouterModule],
  templateUrl: './booking-wizard.component.html',
  styleUrl: './booking-wizard.component.css'
})
export class BookingWizardComponent {
  flightService = inject(FlightService);
  bookingService = inject(BookingService);
  authService = inject(AuthService);

  step = signal(1);
  availableFlights = signal<FlightResponse[]>([]);
  selectedFlight = signal<FlightResponse | null>(null);

  searchFlights(dep: string, arr: string, date: string) {
    this.flightService.searchFlights(dep, arr, date).subscribe({
      next: (flights) => {
        this.availableFlights.set(flights);
        this.step.set(2);
      },
      error: (err) => {
        console.error('Error searching flights', err);
        this.availableFlights.set([]);
        this.step.set(2);
      }
    });
  }

  selectFlight(flight: FlightResponse) {
    this.selectedFlight.set(flight);
    this.step.set(3);
  }

  confirmBooking(requests: string) {
    const user = this.authService.currentUser();
    const flight = this.selectedFlight();
    
    if (!user || !flight) {
      console.error('User or flight not selected');
      return;
    }
    
    this.bookingService.createBooking({
      flightId: flight.id,
      passengerId: user.id,
      specialRequests: requests
    }).subscribe({
      next: () => this.step.set(4),
      error: (err) => {
        console.error('Error creating booking', err);
        alert('An error occurred while confirming your booking. Please try again.');
      }
    });
  }
}
