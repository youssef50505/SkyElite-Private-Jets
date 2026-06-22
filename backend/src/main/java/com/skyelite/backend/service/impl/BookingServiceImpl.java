package com.skyelite.backend.service.impl;

import com.skyelite.backend.dto.request.CreateCharterBookingRequest;
import com.skyelite.backend.dto.response.CharterBookingResponse;
import com.skyelite.backend.entity.CharterBooking;
import com.skyelite.backend.entity.Flight;
import com.skyelite.backend.entity.User;
import com.skyelite.backend.mapper.CharterBookingMapper;
import com.skyelite.backend.repository.CharterBookingRepository;
import com.skyelite.backend.repository.FlightRepository;
import com.skyelite.backend.repository.UserRepository;
import com.skyelite.backend.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@SuppressWarnings("null")
public class BookingServiceImpl implements BookingService {

    private final CharterBookingRepository charterBookingRepository;
    private final UserRepository userRepository;
    private final FlightRepository flightRepository;
    private final CharterBookingMapper bookingMapper;

    @Override
    @Transactional
    public CharterBookingResponse createBooking(CreateCharterBookingRequest request, String passengerEmail) {
        User passenger = userRepository.findByEmail(passengerEmail)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        Flight flight = flightRepository.findById(request.flightId())
                .orElseThrow(() -> new IllegalArgumentException("Flight not found"));

        CharterBooking booking = bookingMapper.toEntity(request);
        booking.setPassenger(passenger);
        booking.setFlight(flight);
        booking.setBookingReference(UUID.randomUUID().toString().substring(0, 8).toUpperCase());
        booking.setBookingDate(LocalDateTime.now());

        CharterBooking saved = charterBookingRepository.save(booking);
        return bookingMapper.toResponse(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CharterBookingResponse> getMyBookings(String passengerEmail) {
        User passenger = userRepository.findByEmail(passengerEmail)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        return bookingMapper.toResponseList(charterBookingRepository.findByPassengerOrderByBookingDateDesc(passenger));
    }

    @Override
    @Transactional(readOnly = true)
    public List<CharterBookingResponse> getAllBookings() {
        return bookingMapper.toResponseList(charterBookingRepository.findAll());
    }

    @Override
    @Transactional
    public void updateBookingStatus(String reference, String status) {
        CharterBooking booking = charterBookingRepository.findByBookingReference(reference)
                .orElseThrow(() -> new IllegalArgumentException("Booking not found"));
        booking.setStatus(CharterBooking.BookingStatus.valueOf(status.toUpperCase()));
        charterBookingRepository.save(booking);
    }
}
