package com.skyelite.backend.config;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableCaching
public class CacheConfig {
    // Relying on Spring Boot's simple concurrent map cache for now.
    // Can be upgraded to Redis by adding spring-boot-starter-data-redis
}
