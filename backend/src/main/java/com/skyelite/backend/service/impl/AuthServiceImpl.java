package com.skyelite.backend.service.impl;

import com.skyelite.backend.dto.request.LoginRequest;
import com.skyelite.backend.dto.request.RegisterRequest;
import com.skyelite.backend.dto.response.JwtAuthenticationResponse;
import com.skyelite.backend.entity.User;
import com.skyelite.backend.repository.UserRepository;
import com.skyelite.backend.security.JwtUtils;
import com.skyelite.backend.security.UserDetailsImpl;
import com.skyelite.backend.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtils jwtUtils;

    @Override
    public JwtAuthenticationResponse login(LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.email(), loginRequest.password()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        String role = userDetails.getAuthorities().iterator().next().getAuthority().replace("ROLE_", "");

        return new JwtAuthenticationResponse(jwt, "Bearer", userDetails.getUsername(), role);
    }

    @Override
    @Transactional
    public JwtAuthenticationResponse register(RegisterRequest registerRequest) {
        if (userRepository.existsByEmail(registerRequest.email())) {
            throw new IllegalArgumentException("Error: Email is already in use!");
        }

        User user = new User();
        user.setFirstName(registerRequest.firstName());
        user.setLastName(registerRequest.lastName());
        user.setEmail(registerRequest.email());
        user.setPasswordHash(passwordEncoder.encode(registerRequest.password()));
        user.setPhoneNumber(registerRequest.phoneNumber());
        user.setRole(registerRequest.role());

        userRepository.save(user);

        return login(new LoginRequest(registerRequest.email(), registerRequest.password()));
    }
}
