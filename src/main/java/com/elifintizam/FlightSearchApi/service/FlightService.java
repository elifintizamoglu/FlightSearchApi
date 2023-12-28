package com.elifintizam.FlightSearchApi.service;

import com.elifintizam.FlightSearchApi.model.Flight;
import com.elifintizam.FlightSearchApi.repository.FlightRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Service
public class FlightService {

    private final FlightRepository flightRepository;

    @Autowired
    public FlightService(FlightRepository flightRepository) {
        this.flightRepository = flightRepository;
    }

    public List<Flight> getFlights(){
        return flightRepository.findAll();
    }

    public Flight getFlight(Long flightId) {
        return flightRepository.findById(flightId).orElseThrow(()->
                new IllegalStateException("Flight with id " + flightId + " does not exists."));
    }

    public void addFlight(Flight flight) {
        flightRepository.save(flight);
    }

    public void deleteFlight(Long flightId) {
        boolean exists = flightRepository.existsById(flightId);
        if (!exists) {
            throw new IllegalStateException("Flight with id " + flightId + " does not exists.");
        }
        flightRepository.deleteById(flightId);
    }

    @Transactional
    public void updateFlight(Long flightId,
                             String departureAirport,
                             String destinationAirport,
                             LocalDateTime departureTime,
                             LocalDateTime returnTime,
                             Float price) {
        Flight flight = flightRepository.findById(flightId)
                .orElseThrow(()->new IllegalStateException("Flight with id " + flightId + " does not exists."));

        if(departureAirport != null &&
                departureAirport.length() > 0 &&
                !Objects.equals(flight.getDepartureAirport(),departureAirport) &&
                departureAirport != flight.getDestinationAirport()){

            flight.setDepartureAirport(departureAirport);
        }

        if(destinationAirport != null &&
                destinationAirport.length() > 0 &&
                !Objects.equals(flight.getDestinationAirport(),destinationAirport) &&
                destinationAirport != flight.getDepartureAirport()){

            flight.setDestinationAirport(destinationAirport);
        }

        if(departureTime != null &&
                !departureTime.isAfter(LocalDateTime.now()) &&
                !Objects.equals(flight.getDepartureTime(),departureTime)){

            flight.setDepartureTime(departureTime);
        }

        if(returnTime != null &&
                !returnTime.isAfter(LocalDateTime.now()) &&
                !Objects.equals(flight.getReturnTime(),returnTime) &&
                !returnTime.isAfter(flight.getDepartureTime())){

            flight.setReturnTime(returnTime);
        }

        if(price != null && price != flight.getPrice() && price>0){
            flight.setPrice(price);
        }
    }


}
