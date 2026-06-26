package com.skyelite.backend.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import org.jspecify.annotations.Nullable;
import java.math.BigDecimal;

@Entity
@Table(name = "airports")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Airport extends BaseEntity {

    @Id
    @Column(length = 3, unique = true, nullable = false)
    private String iataCode;

    @Column(unique = true)
    private @Nullable String icaoCode;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String city;

    @Column(nullable = false)
    private String country;

    @Nullable
    @Column(precision = 10, scale = 6)
    private BigDecimal latitude;

    @Nullable
    @Column(precision = 10, scale = 6)
    private BigDecimal longitude;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal landingFee;
}
