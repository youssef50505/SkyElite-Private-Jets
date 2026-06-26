package com.skyelite.backend.service.impl;

import com.skyelite.backend.service.AnalyticsService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class AnalyticsServiceImpl implements AnalyticsService {

    @Override
    public Map<String, Object> getOperationsCommandMetrics() {
        Map<String, Object> metrics = new HashMap<>();
        metrics.put("globalLoadFactor", 0.92);
        metrics.put("onTimePerformance", 0.98);
        metrics.put("dailyRevenue", 42500);
        metrics.put("activeFleets", 14);
        return metrics;
    }

    @Override
    public Map<String, Object> getRevenueManagementMetrics() {
        Map<String, Object> metrics = new HashMap<>();
        metrics.put("totalRevenue", 1250000);
        metrics.put("avgOccupancy", 0.85);
        metrics.put("rasm", 0.15);
        metrics.put("activePricingRules", 3);
        return metrics;
    }
}
