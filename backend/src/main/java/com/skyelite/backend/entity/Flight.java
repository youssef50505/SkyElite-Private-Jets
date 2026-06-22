package com.skyelite.backend.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import org.jspecify.annotations.Nullable;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "flights")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Flight extends BaseEntity {

    public enum FlightType {
        CHARTER,
        EMPTY_LEG,
        MAINTENANCE
    }

    public enum FlightStatus {
        SCHEDULED,
        EN_ROUTE,
        LANDED,
        CANCELLED
    }

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "aircraft_id", nullable = false)
    private Aircraft aircraft;

    @Column(name = "departure_airport_iata", nullable = false)
    private String departureAirportIata;

    @Column(name = "arrival_airport_iata", nullable = false)
    private String arrivalAirportIata;

    @Column(nullable = false)
    private LocalDateTime scheduledDeparture;

    @Column(nullable = false)
    private LocalDateTime scheduledArrival;

    @Nullable
    @Column(name = "actual_departure")
    private LocalDateTime actualDeparture;

    @Nullable
    @Column(name = "actual_arrival")
    private LocalDateTime actualArrival;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private FlightType flightType;

    @Column(name = "flight_number")
    private String flightNumber;

    @Column(name = "base_price")
    private java.math.BigDecimal basePrice;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private FlightStatus status;
}
