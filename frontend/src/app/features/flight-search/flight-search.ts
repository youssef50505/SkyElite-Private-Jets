import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ActivatedRoute, Router } from '@angular/router';
import { FormBuilder, FormGroup, ReactiveFormsModule } from '@angular/forms';
import { FlightService } from '../../core/services/flight.service';

@Component({
  selector: 'app-flight-search',
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './flight-search.html',
  styleUrl: './flight-search.css',
})
export class FlightSearch implements OnInit {
  searchForm: FormGroup;
  allFlights: any[] = [];
  filteredFlights: any[] = [];
  loading = true;

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private fb: FormBuilder,
    private flightService: FlightService
  ) {
    this.searchForm = this.fb.group({
      origin: [''],
      destination: [''],
      date: [''],
      passengers: ['1']
    });
  }

  ngOnInit(): void {
    this.loadAllFlights();
  }

  private loadAllFlights(): void {
    this.flightService.getAllFlights().subscribe({
      next: (results) => {
        this.allFlights = results;
        this.loading = false;
        
        // After loading all flights, process query params
        this.route.queryParams.subscribe(params => {
          this.searchForm.patchValue({
            origin: params['origin'] || '',
            destination: params['destination'] || '',
            date: params['date'] || '',
            passengers: params['passengers'] || '1'
          });
          this.filterFlights(params['origin'], params['destination'], params['date']);
        });
      },
      error: (err) => {
        console.error('Error fetching flights:', err);
        this.loading = false;
      }
    });
  }

  onSearchSubmit(): void {
    const formValue = this.searchForm.value;
    // Update URL query params without reloading the page, triggering the subscription
    this.router.navigate([], {
      relativeTo: this.route,
      queryParams: {
        origin: formValue.origin,
        destination: formValue.destination,
        date: formValue.date,
        passengers: formValue.passengers
      },
      queryParamsHandling: 'merge'
    });
  }

  private filterFlights(origin?: string, destination?: string, date?: string): void {
    this.filteredFlights = this.allFlights.filter(f => {
      const matchOrigin = !origin || f.departureAirportIata.toLowerCase().includes(origin.toLowerCase());
      const matchDest = !destination || f.arrivalAirportIata.toLowerCase().includes(destination.toLowerCase());
      const matchDate = !date || (f.scheduledDeparture && f.scheduledDeparture.startsWith(date));
      return matchOrigin && matchDest && matchDate;
    });
  }
}
