package com.skyelite.backend.service.impl;

import com.skyelite.backend.dto.request.CreateFlightRequest;
import com.skyelite.backend.dto.response.FlightResponse;
import com.skyelite.backend.entity.Aircraft;
import com.skyelite.backend.entity.Flight;
import com.skyelite.backend.mapper.FlightMapper;
import com.skyelite.backend.repository.AircraftRepository;
import com.skyelite.backend.repository.FlightRepository;
import com.skyelite.backend.service.FlightOperationsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@SuppressWarnings("null")
public class FlightOperationsServiceImpl implements FlightOperationsService {

    private final FlightRepository flightRepository;
    private final AircraftRepository aircraftRepository;
    private final FlightMapper flightMapper;

    @Override
    @Transactional
    public FlightResponse scheduleFlight(CreateFlightRequest request) {
        Aircraft aircraft = aircraftRepository.findById(request.aircraftId())
                .orElseThrow(() -> new IllegalArgumentException("Aircraft not found with ID: " + request.aircraftId()));

        Flight flight = flightMapper.toEntity(request);
        flight.setAircraft(aircraft);

        Flight saved = flightRepository.save(flight);
        return flightMapper.toResponse(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public List<FlightResponse> getAllFlights() {
        return flightMapper.toResponseList(flightRepository.findAll());
    }

    @Override
    public List<FlightResponse> searchFlights(String origin, String destination) {
        return flightMapper.toResponseList(flightRepository.findByDepartureAirportIataAndArrivalAirportIata(origin, destination));
    }

    @Override
    @Transactional(readOnly = true)
    public List<FlightResponse> getFeaturedEmptyLegs() {
        return flightMapper.toResponseList(
            flightRepository.findTop5ByFlightTypeAndStatusOrderByScheduledDepartureAsc(
                Flight.FlightType.EMPTY_LEG, Flight.FlightStatus.SCHEDULED
            )
        );
    }

    @Override
    @Transactional
    public void updateFlightStatus(UUID flightId, String status) {
        Flight flight = flightRepository.findById(flightId)
                .orElseThrow(() -> new IllegalArgumentException("Flight not found with ID: " + flightId));
        flight.setStatus(Flight.FlightStatus.valueOf(status.toUpperCase()));
        flightRepository.save(flight);
    }
}
