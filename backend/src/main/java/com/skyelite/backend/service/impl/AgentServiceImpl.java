package com.skyelite.backend.service.impl;

import com.skyelite.backend.service.AgentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AgentServiceImpl implements AgentService {
    @Override
    public List<Map<String, Object>> getClientPortfolio() {
        return Collections.emptyList();
    }
}
