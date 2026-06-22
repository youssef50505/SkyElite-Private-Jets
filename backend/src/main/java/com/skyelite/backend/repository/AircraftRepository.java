package com.skyelite.backend.repository;

import com.skyelite.backend.entity.Aircraft;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface AircraftRepository extends JpaRepository<Aircraft, UUID> {
    Optional<Aircraft> findByTailNumber(String tailNumber);
    List<Aircraft> findByStatus(Aircraft.AircraftStatus status);
}
