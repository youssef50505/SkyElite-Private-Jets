package com.skyelite.backend.dto.request;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record CreateAircraftRequest(
        @NotBlank String tailNumber,
        @NotBlank String model,
        @NotNull @Min(1) Integer passengerCapacity,
        @NotNull @Min(100) Integer rangeNauticalMiles,
        @NotNull @DecimalMin("100.0") BigDecimal hourlyRate,
        @NotBlank String currentLocationIata
) {}
