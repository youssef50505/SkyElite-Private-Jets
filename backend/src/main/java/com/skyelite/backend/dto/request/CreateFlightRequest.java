package com.skyelite.backend.dto.request;

import com.skyelite.backend.entity.Flight;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;
import java.util.UUID;

public record CreateFlightRequest(
        @NotNull UUID aircraftId,
        @NotBlank @Size(min = 3, max = 3) String departureAirportIata,
        @NotBlank @Size(min = 3, max = 3) String arrivalAirportIata,
        @NotNull LocalDateTime scheduledDeparture,
        @NotNull LocalDateTime scheduledArrival,
        @NotNull Flight.FlightType flightType
) {}
