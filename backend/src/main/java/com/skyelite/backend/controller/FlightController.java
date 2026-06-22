package com.skyelite.backend.controller;

import com.skyelite.backend.dto.request.CreateFlightRequest;
import com.skyelite.backend.dto.response.FlightResponse;
import com.skyelite.backend.service.FlightOperationsService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/flights")
@RequiredArgsConstructor
public class FlightController {

    private final FlightOperationsService flightOperationsService;

    @GetMapping
    public ResponseEntity<List<FlightResponse>> getAllFlights() {
        return ResponseEntity.ok(flightOperationsService.getAllFlights());
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('OPERATIONS', 'ADMIN')")
    public ResponseEntity<FlightResponse> scheduleFlight(@Valid @RequestBody CreateFlightRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(flightOperationsService.scheduleFlight(request));
    }

    @PatchMapping("/{id}/status")
    @PreAuthorize("hasAnyRole('OPERATIONS', 'ADMIN')")
    public ResponseEntity<Void> updateStatus(@PathVariable UUID id, @RequestParam String status) {
        flightOperationsService.updateFlightStatus(id, status);
        return ResponseEntity.noContent().build();
    }
}
