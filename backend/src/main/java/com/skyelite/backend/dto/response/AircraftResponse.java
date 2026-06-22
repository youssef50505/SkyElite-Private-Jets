package com.skyelite.backend.dto.response;

import com.skyelite.backend.entity.Aircraft;

import java.math.BigDecimal;
import java.util.UUID;

public record AircraftResponse(
        UUID id,
        String tailNumber,
        String model,
        Integer passengerCapacity,
        Integer rangeNauticalMiles,
        BigDecimal hourlyRate,
        String currentLocationIata,
        String imageUrl,
        Aircraft.AircraftStatus status
) {}
