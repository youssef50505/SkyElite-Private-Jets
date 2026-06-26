package com.skyelite.backend.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import org.jspecify.annotations.Nullable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
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

    @Column(name = "actual_departure")
    private @Nullable LocalDateTime actualDeparture;

    @Column(name = "actual_arrival")
    private @Nullable LocalDateTime actualArrival;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private FlightType flightType;

    @Column(name = "flight_number")
    private String flightNumber;

    @Column(name = "stops_count", nullable = false)
    private Integer stopsCount = 0;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "flight_amenities", joinColumns = @JoinColumn(name = "flight_id"))
    @Column(name = "amenity")
    private List<String> amenities;

    @Column(name = "economy_price")
    private BigDecimal economyPrice;

    @Column(name = "premium_economy_price")
    private @Nullable BigDecimal premiumEconomyPrice;

    @Column(name = "business_price")
    private @Nullable BigDecimal businessPrice;

    @Column(name = "first_class_price")
    private @Nullable BigDecimal firstClassPrice;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private FlightStatus status;
}
