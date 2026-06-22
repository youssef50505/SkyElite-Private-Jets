package com.skyelite.backend.repository;

import com.skyelite.backend.entity.CharterBooking;
import com.skyelite.backend.entity.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface CharterBookingRepository extends JpaRepository<CharterBooking, UUID> {
    
    @EntityGraph(attributePaths = {"passenger", "flight", "flight.aircraft"})
    List<CharterBooking> findByPassengerOrderByBookingDateDesc(User passenger);
    
    @EntityGraph(attributePaths = {"passenger", "flight", "flight.aircraft"})
    Optional<CharterBooking> findByBookingReference(String bookingReference);
}
