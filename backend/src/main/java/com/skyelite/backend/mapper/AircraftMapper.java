package com.skyelite.backend.mapper;

import com.skyelite.backend.dto.request.CreateAircraftRequest;
import com.skyelite.backend.dto.response.AircraftResponse;
import com.skyelite.backend.entity.Aircraft;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AircraftMapper {

    AircraftResponse toResponse(Aircraft aircraft);
    
    List<AircraftResponse> toResponseList(List<Aircraft> aircrafts);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "status", constant = "AVAILABLE")
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    Aircraft toEntity(CreateAircraftRequest request);
}
