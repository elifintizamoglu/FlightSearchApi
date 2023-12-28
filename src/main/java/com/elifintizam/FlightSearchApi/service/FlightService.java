package com.elifintizam.FlightSearchApi.service;

import com.elifintizam.FlightSearchApi.model.Flight;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FlightService {

    public List<Flight> getFlights(){
        List<Flight> flightList = new ArrayList<>();
        return flightList;
    }
}
