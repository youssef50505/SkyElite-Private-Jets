package com.skyelite.backend.service;

import java.util.Map;

public interface AnalyticsService {
    Map<String, Object> getOperationsCommandMetrics();
    Map<String, Object> getRevenueManagementMetrics();
}
