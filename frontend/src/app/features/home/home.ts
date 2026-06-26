import { Component, OnInit } from '@angular/core';
import { RouterLink, Router } from '@angular/router';
import { CommonModule } from '@angular/common';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { FlightService } from '../../core/services/flight.service';

@Component({
  selector: 'app-home',
  imports: [RouterLink, CommonModule, ReactiveFormsModule],
  templateUrl: './home.html',
  styleUrl: './home.css'
})
export class Home implements OnInit {
  searchForm: FormGroup;
  featuredFlights: any[] = [];
  loadingFeatured = true;

  constructor(
    private fb: FormBuilder,
    private router: Router,
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
    this.flightService.getFeaturedFlights().subscribe({
      next: (flights) => {
        this.featuredFlights = flights;
        this.loadingFeatured = false;
      },
      error: (err) => {
        console.error('Failed to load featured flights', err);
        this.loadingFeatured = false;
      }
    });
  }

  onSearch(): void {
    if (this.searchForm.valid) {
      const formValue = this.searchForm.value;
      this.router.navigate(['/search'], {
        queryParams: {
          origin: formValue.origin,
          destination: formValue.destination,
          date: formValue.date,
          passengers: formValue.passengers
        }
      });
    }
  }
}
