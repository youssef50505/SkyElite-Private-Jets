import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { BookingService } from '../../core/services/booking.service';
import { AuthService } from '../../core/services/auth.service';

@Component({
  selector: 'app-dashboard',
  imports: [CommonModule],
  templateUrl: './dashboard.html',
  styleUrl: './dashboard.css',
})
export class Dashboard implements OnInit {
  userName: string = 'Guest';
  upcomingFlights: any[] = [];
  pastFlights: any[] = [];
  nextTrip: any = null;
  skyRewards: number = 0;
  loading: boolean = true;

  constructor(
    private bookingService: BookingService,
    private authService: AuthService
  ) {}

  ngOnInit(): void {
    const user = this.authService.getCurrentUser();
    if (user && user.email) {
      this.userName = user.email.split('@')[0];
    }

    this.bookingService.getMyBookings().subscribe({
      next: (bookings) => {
        const now = new Date();
        
        bookings.forEach(booking => {
          const departure = new Date(booking.flight.scheduledDeparture);
          if (departure > now && booking.status !== 'CANCELLED') {
            this.upcomingFlights.push(booking);
          } else {
            this.pastFlights.push(booking);
          }
        });

        // Sort upcoming flights by departure time to find the next trip
        this.upcomingFlights.sort((a, b) => {
          return new Date(a.flight.scheduledDeparture).getTime() - new Date(b.flight.scheduledDeparture).getTime();
        });

        if (this.upcomingFlights.length > 0) {
          this.nextTrip = this.upcomingFlights[0];
        }

        // Calculate mock SkyRewards (e.g., 500 points per completed past flight)
        const completedFlights = this.pastFlights.filter(b => b.status === 'COMPLETED');
        this.skyRewards = completedFlights.length * 500;
        
        this.loading = false;
      },
      error: (err) => {
        console.error('Failed to load bookings', err);
        this.loading = false;
      }
    });
  }
}
