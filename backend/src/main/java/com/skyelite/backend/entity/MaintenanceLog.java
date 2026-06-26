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
@Table(name = "maintenance_logs")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MaintenanceLog extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "aircraft_id", nullable = false)
    private Aircraft aircraft;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String description;

    @Column(nullable = false)
    private LocalDateTime startDate;

    @Column(name = "end_date")
    private @Nullable LocalDateTime endDate;

    @Column(precision = 10, scale = 2)
    private @Nullable BigDecimal cost;
}
