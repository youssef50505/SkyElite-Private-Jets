import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ActivatedRoute, Router } from '@angular/router';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { FlightService } from '../../core/services/flight.service';

@Component({
  selector: 'app-flight-search',
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './flight-search.html',
  styleUrl: './flight-search.css',
})
export class FlightSearch implements OnInit {
  searchForm: FormGroup;
  flights: any[] = [];
  loading = false;
  hasSearched = false;

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private fb: FormBuilder,
    private flightService: FlightService
  ) {
    this.searchForm = this.fb.group({
      origin: ['', Validators.required],
      destination: ['', Validators.required],
      date: [''],
      passengers: ['1']
    });
  }

  ngOnInit(): void {
    // Read query params on load to perform initial search if navigating from homepage
    this.route.queryParams.subscribe(params => {
      if (params['origin'] && params['destination']) {
        this.searchForm.patchValue({
          origin: params['origin'],
          destination: params['destination'],
          date: params['date'] || '',
          passengers: params['passengers'] || '1'
        });
        this.performSearch(params['origin'], params['destination']);
      }
    });
  }

  onSearchSubmit(): void {
    if (this.searchForm.valid) {
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
  }

  private performSearch(origin: string, destination: string): void {
    this.loading = true;
    this.hasSearched = true;
    this.flightService.searchFlights(origin, destination).subscribe({
      next: (results) => {
        this.flights = results;
        this.loading = false;
      },
      error: (err) => {
        console.error('Error fetching flights:', err);
        this.flights = [];
        this.loading = false;
      }
    });
  }
}
