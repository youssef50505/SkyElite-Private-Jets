package com.skyelite.backend.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import org.jspecify.annotations.Nullable;
import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "aircrafts")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Aircraft extends BaseEntity {

    public enum AircraftStatus {
        AVAILABLE,
        MAINTENANCE,
        IN_FLIGHT
    }

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false, unique = true)
    private String tailNumber;

    @Column(nullable = false)
    private String model;

    @Column(nullable = false)
    private Integer passengerCapacity;

    @Column(nullable = false)
    private Integer rangeNauticalMiles;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal hourlyRate;

    @Column(name = "current_airport_iata")
    private @Nullable String currentAirportIata;

    @Column(name = "image_url")
    private @Nullable String imageUrl;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AircraftStatus status;
}
