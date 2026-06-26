package com.skyelite.backend.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import org.jspecify.annotations.Nullable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "charter_bookings")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CharterBooking extends BaseEntity {

    public enum BookingStatus {
        PENDING_PAYMENT,
        CONFIRMED,
        COMPLETED,
        CANCELLED
    }

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false, unique = true)
    private String bookingReference;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "passenger_id", nullable = false)
    private User passenger;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "flight_id", nullable = false)
    private Flight flight;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "agent_id")
    private @Nullable User agent;

    @Column(nullable = false)
    private LocalDateTime bookingDate;

    @Column(name = "special_requests")
    private @Nullable String specialRequests;

    @Column(name = "cabin_class")
    private @Nullable String cabinClass;

    @Column(nullable = false, precision = 12, scale = 2)
    private BigDecimal totalAmount;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private BookingStatus status;
}
