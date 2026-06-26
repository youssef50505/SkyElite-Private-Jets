package com.skyelite.backend.mapper;

import com.skyelite.backend.dto.request.CreateFlightRequest;
import com.skyelite.backend.dto.response.FlightResponse;
import com.skyelite.backend.entity.Flight;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = {AircraftMapper.class})
public interface FlightMapper {

    FlightResponse toResponse(Flight flight);
    
    List<FlightResponse> toResponseList(List<Flight> flights);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "aircraft", ignore = true) // Handled in Service
    @Mapping(target = "status", constant = "SCHEDULED")
    @Mapping(target = "actualDeparture", ignore = true)
    @Mapping(target = "actualArrival", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "stopsCount", ignore = true)
    @Mapping(target = "amenities", ignore = true)
    @Mapping(target = "economyPrice", ignore = true)
    @Mapping(target = "premiumEconomyPrice", ignore = true)
    @Mapping(target = "businessPrice", ignore = true)
    @Mapping(target = "firstClassPrice", ignore = true)
    @Mapping(target = "flightNumber", ignore = true)
    Flight toEntity(CreateFlightRequest request);
}
