package com.elifintizam.FlightSearchApi.controller;

import com.elifintizam.FlightSearchApi.model.Flight;
import com.elifintizam.FlightSearchApi.service.FlightService;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping(path = "api/flight")
public class FlightController {

    private final FlightService flightService;

    @Autowired
    public FlightController(FlightService flightService) {
        this.flightService = flightService;
    }

    @GetMapping(path = "/get/all")
    public List<Flight> getFlights() {
        return flightService.getFlights();
    }

    @GetMapping(path = "/get/{flightId}")
    public Flight getFlightById(@PathVariable("flightId") Long flightId) {
        return flightService.getFlightById(flightId);
    }

    @PostMapping(path = "/add")
    public void addFlight(@RequestBody Flight flight) {
        flightService.addFlight(flight);
    }

    @DeleteMapping(path = "/delete/{flightId}")
    public void deleteFlight(@PathVariable("flightId") Long flightId) {
        flightService.deleteFlight(flightId);
    }

    @PutMapping(path = "/update/{flightId}")
    public void updateFlight(@PathVariable("flightId") Long flightId,
                             @RequestParam(required = false) Long departureAirportId,
                             @RequestParam(required = false) Long arrivalAirportId,
                             @RequestParam(required = false) LocalDateTime departureTime,
                             @RequestParam(required = false) LocalDateTime returnTime,
                             @RequestParam(required = false) Float price) {
        flightService.updateFlight(flightId, departureAirportId, arrivalAirportId, departureTime, returnTime, price);
    }

    @GetMapping(path = "/search/")
    public List<Flight> searchFlights(@RequestParam String departureCity,
                                      @RequestParam String arrivalCity,
                                      @RequestParam @JsonFormat(pattern = "yyyy-MM-dd") LocalDateTime departureTime,
                                      @RequestParam(required = false) @JsonFormat(pattern = "yyyy-MM-dd") LocalDateTime returnTime) {
        return flightService.searchFlights(departureCity, arrivalCity, departureTime, returnTime);
    }


}
