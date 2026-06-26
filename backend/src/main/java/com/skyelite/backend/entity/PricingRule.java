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
@Table(name = "pricing_rules")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PricingRule extends BaseEntity {

    public enum RuleType {
        GLOBAL_MULTIPLIER,
        REGIONAL_SURGE
    }

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private RuleType ruleType;

    private @Nullable String conditions;

    @Column(nullable = false, precision = 5, scale = 2)
    private BigDecimal multiplier;

    @Column(name = "is_active", nullable = false)
    private Boolean isActive = true;
}
