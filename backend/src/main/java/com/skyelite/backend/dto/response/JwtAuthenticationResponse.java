package com.skyelite.backend.dto.response;

public record JwtAuthenticationResponse(
        String token,
        String type,
        String email,
        String role
) {}
