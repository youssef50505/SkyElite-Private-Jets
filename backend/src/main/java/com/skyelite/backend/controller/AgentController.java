package com.skyelite.backend.controller;

import com.skyelite.backend.service.AgentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/agent")
@RequiredArgsConstructor
public class AgentController {

    private final AgentService agentService;

    @GetMapping("/clients")
    @PreAuthorize("hasRole('AGENT')")
    public ResponseEntity<List<Map<String, Object>>> getClientPortfolio() {
        return ResponseEntity.ok(agentService.getClientPortfolio());
    }
}
