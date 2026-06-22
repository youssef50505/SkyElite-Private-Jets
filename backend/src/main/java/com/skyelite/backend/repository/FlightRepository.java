package com.skyelite.backend.repository;

import com.skyelite.backend.entity.Flight;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Repository
public interface FlightRepository extends JpaRepository<Flight, UUID> {
    
    @EntityGraph(attributePaths = {"aircraft"})
    List<Flight> findByStatusAndScheduledDepartureBetween(Flight.FlightStatus status, LocalDateTime start, LocalDateTime end);
    
    @EntityGraph(attributePaths = {"aircraft"})
    List<Flight> findByDepartureAirportIataAndArrivalAirportIata(String dep, String arr);
}
