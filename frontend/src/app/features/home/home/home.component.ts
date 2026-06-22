import { Component, AfterViewInit, ElementRef, ViewChild } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import gsap from 'gsap';

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [CommonModule, RouterModule],
  templateUrl: './home.component.html',
  styleUrl: './home.component.css'
})
export class HomeComponent implements AfterViewInit {
  constructor(private el: ElementRef) {}

  ngAfterViewInit() {
    // Initial animations
    const tl = gsap.timeline();

    tl.from(this.el.nativeElement.querySelectorAll('.gsap-fade-up'), {
      y: 50,
      opacity: 0,
      duration: 1,
      stagger: 0.2,
      ease: 'power3.out'
    })
    .from(this.el.nativeElement.querySelector('.gsap-scale-up'), {
      y: 30,
      opacity: 0,
      scale: 0.95,
      duration: 0.8,
      ease: 'power2.out'
    }, '-=0.5');

    // Features stagger
    gsap.from(this.el.nativeElement.querySelectorAll('.gsap-stagger-feature'), {
      y: 40,
      opacity: 0,
      duration: 0.8,
      stagger: 0.15,
      ease: 'power2.out',
      delay: 0.5
    });

    // Escapes stagger
    gsap.from(this.el.nativeElement.querySelectorAll('.gsap-stagger-card'), {
      y: 50,
      opacity: 0,
      duration: 0.8,
      stagger: 0.15,
      ease: 'power2.out',
      delay: 0.8
    });

    // Connections section
    gsap.from(this.el.nativeElement.querySelector('.gsap-slide-right'), {
      x: -50,
      opacity: 0,
      duration: 1,
      ease: 'power3.out',
      delay: 1
    });

    gsap.from(this.el.nativeElement.querySelector('.gsap-slide-left'), {
      x: 50,
      opacity: 0,
      duration: 1,
      ease: 'power3.out',
      delay: 1
    });
  }
}
