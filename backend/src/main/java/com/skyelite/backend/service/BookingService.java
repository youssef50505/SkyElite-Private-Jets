package com.skyelite.backend.service;

import com.skyelite.backend.dto.request.CreateCharterBookingRequest;
import com.skyelite.backend.dto.response.CharterBookingResponse;

import java.util.List;

public interface BookingService {
    CharterBookingResponse createBooking(CreateCharterBookingRequest request, String passengerEmail);
    List<CharterBookingResponse> getMyBookings(String passengerEmail);
    List<CharterBookingResponse> getAllBookings();
    void updateBookingStatus(String reference, String status);
}
