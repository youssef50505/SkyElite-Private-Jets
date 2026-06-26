package com.skyelite.backend.dto.response;

import com.skyelite.backend.entity.enums.LoyaltyStatus;
import com.skyelite.backend.entity.enums.Role;

import java.util.UUID;

public record UserResponse(
        UUID id,
        String firstName,
        String lastName,
        String email,
        String phoneNumber,
        Role role,
        LoyaltyStatus loyaltyStatus
) {}
