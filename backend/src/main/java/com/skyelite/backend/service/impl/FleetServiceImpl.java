package com.skyelite.backend.service.impl;

import com.skyelite.backend.dto.request.CreateAircraftRequest;
import com.skyelite.backend.dto.response.AircraftResponse;
import com.skyelite.backend.entity.Aircraft;
import com.skyelite.backend.mapper.AircraftMapper;
import com.skyelite.backend.repository.AircraftRepository;
import com.skyelite.backend.service.FleetService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@SuppressWarnings("null")
public class FleetServiceImpl implements FleetService {

    private final AircraftRepository aircraftRepository;
    private final AircraftMapper aircraftMapper;

    @Override
    @Transactional
    public AircraftResponse addAircraft(CreateAircraftRequest request) {
        Aircraft aircraft = aircraftMapper.toEntity(request);
        Aircraft saved = aircraftRepository.save(aircraft);
        return aircraftMapper.toResponse(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public List<AircraftResponse> getAllAircrafts() {
        return aircraftMapper.toResponseList(aircraftRepository.findAll());
    }

    @Override
    @Transactional(readOnly = true)
    public AircraftResponse getAircraftById(UUID id) {
        Aircraft aircraft = aircraftRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Aircraft not found with ID: " + id));
        return aircraftMapper.toResponse(aircraft);
    }

    @Override
    @Transactional
    public void updateAircraftStatus(UUID id, String status) {
        Aircraft aircraft = aircraftRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Aircraft not found with ID: " + id));
        aircraft.setStatus(Aircraft.AircraftStatus.valueOf(status.toUpperCase()));
        aircraftRepository.save(aircraft);
    }
}
