package com.skyelite.backend.service;

import com.skyelite.backend.dto.request.CreateFlightRequest;
import com.skyelite.backend.dto.response.FlightResponse;

import java.util.List;
import java.util.UUID;

public interface FlightOperationsService {
    FlightResponse scheduleFlight(CreateFlightRequest request);
    List<FlightResponse> getAllFlights();
    void updateFlightStatus(UUID flightId, String status);
}
