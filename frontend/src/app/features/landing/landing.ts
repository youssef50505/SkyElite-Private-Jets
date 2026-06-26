import { Component, AfterViewInit, ElementRef, ViewChild } from '@angular/core';
import { RouterLink } from '@angular/router';
import gsap from 'gsap';

@Component({
  selector: 'app-landing',
  imports: [RouterLink],
  templateUrl: './landing.html',
  styleUrl: './landing.css'
})
export class Landing implements AfterViewInit {
  @ViewChild('heroContent') heroContent!: ElementRef;
  @ViewChild('airplaneImg') airplaneImg!: ElementRef;

  ngAfterViewInit() {
    gsap.from(this.heroContent.nativeElement.children, {
      y: 50,
      opacity: 0,
      duration: 1,
      stagger: 0.2,
      ease: 'power3.out'
    });

    gsap.from(this.airplaneImg.nativeElement, {
      x: -100,
      opacity: 0,
      duration: 1.5,
      ease: 'power2.out',
      delay: 0.5
    });
  }
}
