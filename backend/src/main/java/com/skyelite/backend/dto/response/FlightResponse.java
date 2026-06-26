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
        Integer stopsCount,
        java.util.List<String> amenities,
        java.math.BigDecimal economyPrice,
        java.math.BigDecimal premiumEconomyPrice,
        java.math.BigDecimal businessPrice,
        java.math.BigDecimal firstClassPrice,
        Flight.FlightStatus status
) {}
