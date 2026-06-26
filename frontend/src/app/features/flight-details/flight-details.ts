import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ActivatedRoute, Router, RouterModule } from '@angular/router';
import { FlightService } from '../../core/services/flight.service';

@Component({
  selector: 'app-flight-details',
  imports: [CommonModule, RouterModule],
  templateUrl: './flight-details.html',
  styleUrl: './flight-details.css',
})
export class FlightDetails implements OnInit {
  flight: any = null;
  loading = true;

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private flightService: FlightService
  ) {}

  ngOnInit(): void {
    const flightId = this.route.snapshot.paramMap.get('id');
    if (flightId) {
      this.flightService.getFlightById(flightId).subscribe({
        next: (data) => {
          this.flight = data;
          this.loading = false;
        },
        error: (err) => {
          console.error('Error loading flight:', err);
          this.loading = false;
        }
      });
    } else {
      this.loading = false;
    }
  }

  bookFlight(): void {
    alert('Booking flow will be implemented in a future update.');
  }
}
