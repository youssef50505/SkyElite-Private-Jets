package com.skyelite.backend.dto.request;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.UUID;

public record CreateCharterBookingRequest(
        @NotNull UUID flightId,
        String specialRequests,
        @NotNull @DecimalMin("100.0") BigDecimal totalAmount
) {}
