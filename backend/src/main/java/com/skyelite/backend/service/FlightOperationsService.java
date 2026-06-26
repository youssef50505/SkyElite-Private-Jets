package com.skyelite.backend.service;

import com.skyelite.backend.dto.request.CreateFlightRequest;
import com.skyelite.backend.dto.response.FlightResponse;

import java.util.List;
import java.util.UUID;

public interface FlightOperationsService {
    FlightResponse scheduleFlight(CreateFlightRequest request);
    List<FlightResponse> getAllFlights();
    List<FlightResponse> searchFlights(String origin, String destination);
    List<FlightResponse> getFeaturedEmptyLegs();
    FlightResponse getFlightById(UUID id);
    void updateFlightStatus(UUID flightId, String status);
}
