package com.skyelite.backend.mapper;

import com.skyelite.backend.dto.request.CreateCharterBookingRequest;
import com.skyelite.backend.dto.response.CharterBookingResponse;
import com.skyelite.backend.entity.CharterBooking;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = {FlightMapper.class, UserMapper.class})
public interface CharterBookingMapper {

    CharterBookingResponse toResponse(CharterBooking booking);
    
    List<CharterBookingResponse> toResponseList(List<CharterBooking> bookings);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "bookingReference", ignore = true) // Handled in Service
    @Mapping(target = "passenger", ignore = true) // Handled in Service
    @Mapping(target = "flight", ignore = true) // Handled in Service
    @Mapping(target = "agent", ignore = true)
    @Mapping(target = "bookingDate", ignore = true) // Handled in Service
    @Mapping(target = "status", constant = "PENDING_PAYMENT")
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    CharterBooking toEntity(CreateCharterBookingRequest request);
}
