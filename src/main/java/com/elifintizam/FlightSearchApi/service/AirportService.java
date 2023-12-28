package com.elifintizam.FlightSearchApi.service;

import com.elifintizam.FlightSearchApi.model.Airport;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AirportService {

    public List<Airport> getAirports(){
        List<Airport> airportList = new ArrayList<>();
        return  airportList;
    }
}
