package com.skyelite.backend.service;

import com.skyelite.backend.dto.request.CreateAircraftRequest;
import com.skyelite.backend.dto.response.AircraftResponse;

import java.util.List;
import java.util.UUID;

public interface FleetService {
    AircraftResponse addAircraft(CreateAircraftRequest request);
    List<AircraftResponse> getAllAircrafts();
    AircraftResponse getAircraftById(UUID id);
    void updateAircraftStatus(UUID id, String status);
}
