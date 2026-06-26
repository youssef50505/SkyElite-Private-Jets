package com.skyelite.backend.dto.response;

import com.skyelite.backend.entity.CharterBooking;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record CharterBookingResponse(
        UUID id,
        String bookingReference,
        FlightResponse flight,
        UserResponse agent,
        LocalDateTime bookingDate,
        String specialRequests,
        String cabinClass,
        BigDecimal totalAmount,
        CharterBooking.BookingStatus status
) {}
