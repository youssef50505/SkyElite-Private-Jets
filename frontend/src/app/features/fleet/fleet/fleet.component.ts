import { Component, inject, OnInit, signal, AfterViewInit, ElementRef, ViewChildren, QueryList } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FleetService, AircraftResponse } from '../../../core/services/fleet.service';
import gsap from 'gsap';

@Component({
  selector: 'app-fleet',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './fleet.component.html',
  styleUrl: './fleet.component.css'
})
export class FleetComponent implements OnInit {
  fleetService = inject(FleetService);
  aircrafts = signal<AircraftResponse[]>([]);
  isLoading = signal<boolean>(true);

  constructor(private el: ElementRef) {}

  ngOnInit() {
    this.fleetService.getAllAircraft().subscribe({
      next: (data) => {
        this.aircrafts.set(data);
        this.isLoading.set(false);
        // Delay animation to allow DOM update
        setTimeout(() => this.animateCards(), 50);
      },
      error: (err) => {
        console.error('Failed to load fleet data', err);
        this.isLoading.set(false);
      }
    });

    // Initial header animation
    gsap.from(this.el.nativeElement.querySelectorAll('.gsap-fade-up'), {
      y: 30,
      opacity: 0,
      duration: 0.8,
      stagger: 0.2,
      ease: 'power3.out'
    });
  }

  animateCards() {
    const cards = this.el.nativeElement.querySelectorAll('.gsap-stagger-card');
    if (cards.length > 0) {
      gsap.from(cards, {
        y: 50,
        opacity: 0,
        duration: 0.8,
        stagger: 0.1,
        ease: 'power2.out'
      });
    }
  }
}
