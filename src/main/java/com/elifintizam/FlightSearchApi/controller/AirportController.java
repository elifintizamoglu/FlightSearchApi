package com.elifintizam.FlightSearchApi.controller;

import com.elifintizam.FlightSearchApi.model.Airport;
import com.elifintizam.FlightSearchApi.service.AirportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "api/airport")
public class AirportController {

    private final AirportService airportService;

    @Autowired
    public AirportController(AirportService airportService){
        this.airportService = airportService;
    }

    @GetMapping
    public List<Airport> getAirports(){
        return airportService.getAirports();
    }


}
