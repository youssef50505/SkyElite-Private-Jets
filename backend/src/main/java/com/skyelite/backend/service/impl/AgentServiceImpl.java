package com.skyelite.backend.service.impl;

import com.skyelite.backend.service.AgentService;
import lombok.RequiredArgsConstructor;
import com.skyelite.backend.repository.UserRepository;
import com.skyelite.backend.entity.enums.Role;
import com.skyelite.backend.entity.User;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AgentServiceImpl implements AgentService {
    
    private final UserRepository userRepository;

    @Override
    public List<Map<String, Object>> getClientPortfolio() {
        List<User> passengers = userRepository.findByRole(Role.PASSENGER);
        
        return passengers.stream().map(user -> {
            Map<String, Object> map = new HashMap<>();
            map.put("id", user.getId());
            map.put("firstName", user.getFirstName());
            map.put("lastName", user.getLastName());
            map.put("email", user.getEmail());
            map.put("phoneNumber", user.getPhoneNumber());
            map.put("loyaltyStatus", user.getLoyaltyStatus() != null ? user.getLoyaltyStatus().name() : "NONE");
            return map;
        }).collect(Collectors.toList());
    }
}
