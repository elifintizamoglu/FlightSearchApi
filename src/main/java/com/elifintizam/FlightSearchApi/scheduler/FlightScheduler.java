package com.elifintizam.FlightSearchApi.scheduler;

import com.elifintizam.FlightSearchApi.model.Flight;
import com.elifintizam.FlightSearchApi.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class FlightScheduler {

    private final FlightService flightService;
    private final RestTemplate restTemplate;

    @Autowired
    public FlightScheduler(FlightService flightService, RestTemplate restTemplate) {
        this.flightService = flightService;
        this.restTemplate = restTemplate;
    }

    @Scheduled(cron = "0 0 0 * * ?")
    public void getAndSaveFlights() {

        String mockApiUrl = "https://run.mocky.io/v3/cfcce97c-be03-417e-bb36-17e6d7159cf5";

        ResponseEntity<List<Flight>> responseEntity =
                restTemplate.exchange(
                        mockApiUrl,
                        HttpMethod.GET,
                        null,
                        new ParameterizedTypeReference<List<Flight>>() {
                        }
                );

        List<Flight> flights = responseEntity.getBody();

        for (Flight flight : flights) {
            flightService.addFlight(flight);
        }
    }
}
