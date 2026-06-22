package com.skyelite.backend.client;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
@Slf4j
public class AviationStackClient {

    private final RestClient restClient;

    @Value("${aviationstack.api.key:dummy_key}")
    private String apiKey;

    public AviationStackClient(RestClient.Builder restClientBuilder) {
        this.restClient = restClientBuilder.baseUrl("http://api.aviationstack.com/v1").build();
    }

    @Cacheable(value = "liveFlights", key = "#flightIata", unless = "#result == null")
    public String getLiveFlightData(String flightIata) {
        try {
            log.info("Fetching live flight data for {} from AviationStack API...", flightIata);
            return restClient.get()
                    .uri(uriBuilder -> uriBuilder
                            .path("/flights")
                            .queryParam("access_key", apiKey)
                            .queryParam("flight_iata", flightIata)
                            .build())
                    .retrieve()
                    .body(String.class);
        } catch (Exception e) {
            log.error("Error fetching live flight data from AviationStack: {}", e.getMessage());
            // Graceful degradation: return empty or cached JSON struct
            return "{\"error\": \"Live tracking currently unavailable\"}";
        }
    }
}
