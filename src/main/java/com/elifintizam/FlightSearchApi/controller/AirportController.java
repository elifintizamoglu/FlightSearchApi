package com.elifintizam.FlightSearchApi.controller;

import com.elifintizam.FlightSearchApi.model.Airport;
import com.elifintizam.FlightSearchApi.service.AirportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/airport")
public class AirportController {

    private final AirportService airportService;

    @Autowired
    public AirportController(AirportService airportService){
        this.airportService = airportService;
    }

    @GetMapping(path = "/get/all")
    public List<Airport> getAirports(){
        return airportService.getAirports();
    }

    @GetMapping(path = "/get/{airportId}")
    public Airport getAirport(@PathVariable("airportId") Long airportId){
        return airportService.getAirport(airportId);
    }

    @PostMapping(path = "/add")
    public void addAirport(@RequestBody Airport airport){
        airportService.addAirport(airport);
    }

    @DeleteMapping(path = "/delete/{airportId}")
    public void deleteAirport(@PathVariable("airportId") Long airportId){
        airportService.deleteAirport(airportId);
    }

    @PutMapping(path = "/update/{airportId}")
    public void updateAirport(@PathVariable("airportId") Long airportId,
                              @RequestParam String city){
        airportService.updateAirport(airportId,city);
    }


}
