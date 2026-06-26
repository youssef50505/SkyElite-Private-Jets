package com.skyelite.backend.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import org.jspecify.annotations.Nullable;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "passenger_profiles")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PassengerProfile extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "date_of_birth")
    private @Nullable LocalDate dateOfBirth;

    private @Nullable String gender;

    @Column(name = "passport_number")
    private @Nullable String passportNumber;

    @Column(name = "passport_expiry_date")
    private @Nullable LocalDate passportExpiryDate;

    private @Nullable String nationality;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "passenger_preferences", joinColumns = @JoinColumn(name = "profile_id"))
    @Column(name = "preference")
    private List<String> preferences;
}
