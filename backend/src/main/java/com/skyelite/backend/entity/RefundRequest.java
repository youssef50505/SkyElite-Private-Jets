package com.skyelite.backend.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "refund_requests")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RefundRequest extends BaseEntity {

    public enum RefundPriority {
        HIGH,
        MEDIUM,
        LOW
    }

    public enum RefundStatus {
        PENDING,
        APPROVED,
        REJECTED
    }

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "passenger_id", nullable = false)
    private User passenger;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "booking_id", nullable = false)
    private CharterBooking booking;

    @Column(nullable = false, precision = 12, scale = 2)
    private BigDecimal amount;

    @Column(nullable = false)
    private String reason;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private RefundPriority priority;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private RefundStatus status;
}
