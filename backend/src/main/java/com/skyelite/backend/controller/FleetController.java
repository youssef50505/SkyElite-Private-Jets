package com.skyelite.backend.controller;

import com.skyelite.backend.dto.request.CreateAircraftRequest;
import com.skyelite.backend.dto.response.AircraftResponse;
import com.skyelite.backend.service.FleetService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/aircrafts")
@RequiredArgsConstructor
public class FleetController {

    private final FleetService fleetService;

    @GetMapping
    @PreAuthorize("hasAnyRole('OPERATIONS', 'ADMIN')")
    public ResponseEntity<List<AircraftResponse>> getAllAircrafts() {
        return ResponseEntity.ok(fleetService.getAllAircrafts());
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('OPERATIONS', 'ADMIN')")
    public ResponseEntity<AircraftResponse> addAircraft(@Valid @RequestBody CreateAircraftRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(fleetService.addAircraft(request));
    }

    @PatchMapping("/{id}/status")
    @PreAuthorize("hasAnyRole('OPERATIONS', 'ADMIN')")
    public ResponseEntity<Void> updateStatus(@PathVariable UUID id, @RequestParam String status) {
        fleetService.updateAircraftStatus(id, status);
        return ResponseEntity.noContent().build();
    }
}
