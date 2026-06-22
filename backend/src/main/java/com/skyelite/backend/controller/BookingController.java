package com.skyelite.backend.controller;

import com.skyelite.backend.dto.request.CreateCharterBookingRequest;
import com.skyelite.backend.dto.response.CharterBookingResponse;
import com.skyelite.backend.service.BookingService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/bookings")
@RequiredArgsConstructor
public class BookingController {

    private final BookingService bookingService;

    @PostMapping
    @PreAuthorize("hasRole('PASSENGER')")
    public ResponseEntity<CharterBookingResponse> createBooking(@Valid @RequestBody CreateCharterBookingRequest request, Authentication authentication) {
        return ResponseEntity.status(HttpStatus.CREATED).body(bookingService.createBooking(request, authentication.getName()));
    }

    @GetMapping("/me")
    @PreAuthorize("hasRole('PASSENGER')")
    public ResponseEntity<List<CharterBookingResponse>> getMyBookings(Authentication authentication) {
        return ResponseEntity.ok(bookingService.getMyBookings(authentication.getName()));
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('AGENT', 'OPERATIONS', 'ADMIN')")
    public ResponseEntity<List<CharterBookingResponse>> getAllBookings() {
        return ResponseEntity.ok(bookingService.getAllBookings());
    }

    @PatchMapping("/{reference}/status")
    @PreAuthorize("hasAnyRole('AGENT', 'OPERATIONS', 'ADMIN')")
    public ResponseEntity<Void> updateStatus(@PathVariable String reference, @RequestParam String status) {
        bookingService.updateBookingStatus(reference, status);
        return ResponseEntity.noContent().build();
    }
}
