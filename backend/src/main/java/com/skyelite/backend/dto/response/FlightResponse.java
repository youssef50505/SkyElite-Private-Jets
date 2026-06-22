package com.skyelite.backend.dto.response;

import com.skyelite.backend.entity.Flight;

import java.time.LocalDateTime;
import java.util.UUID;

public record FlightResponse(
        UUID id,
        AircraftResponse aircraft,
        String departureAirportIata,
        String arrivalAirportIata,
        LocalDateTime scheduledDeparture,
        LocalDateTime scheduledArrival,
        LocalDateTime actualDeparture,
        LocalDateTime actualArrival,
        Flight.FlightType flightType,
        String flightNumber,
        java.math.BigDecimal basePrice,
        Flight.FlightStatus status
) {}
