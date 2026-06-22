package com.skyelite.backend.service;

import com.skyelite.backend.dto.request.LoginRequest;
import com.skyelite.backend.dto.request.RegisterRequest;
import com.skyelite.backend.dto.response.JwtAuthenticationResponse;

public interface AuthService {
    JwtAuthenticationResponse login(LoginRequest loginRequest);
    JwtAuthenticationResponse register(RegisterRequest registerRequest);
}
