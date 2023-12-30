package com.elifintizam.FlightSearchApi.service;

import com.elifintizam.FlightSearchApi.model.Airport;
import com.elifintizam.FlightSearchApi.model.Flight;
import com.elifintizam.FlightSearchApi.repository.AirportRepository;
import com.elifintizam.FlightSearchApi.repository.FlightRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class FlightService {

    private final FlightRepository flightRepository;
    private final AirportRepository airportRepository;

    @Autowired
    public FlightService(FlightRepository flightRepository, AirportRepository airportRepository) {
        this.flightRepository = flightRepository;
        this.airportRepository = airportRepository;
    }

    public List<Flight> getFlights() {
        return flightRepository.findAll();
    }

    public Flight getFlightById(Long flightId) {
        return flightRepository.findById(flightId).orElseThrow(() ->
                new IllegalStateException("Flight with id " + flightId + " does not exists."));
    }

    public void addFlight(Flight flight) {
        if (airportRepository.existsById(flight.getDepartureAirport().getAirportId()) &&
                airportRepository.existsById(flight.getArrivalAirport().getAirportId()) &&
                flight.getDepartureAirport().getAirportId() != flight.getArrivalAirport().getAirportId() &&
                flight.getDepartureTime().isAfter(LocalDateTime.now()) &&
                flight.getReturnTime().isAfter(flight.getDepartureTime())) {
            flightRepository.save(flight);
        } else {
            throw new IllegalStateException("Flight with that information can not be created.");
        }
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
                             Long departureAirportId,
                             Long arrivalAirportId,
                             LocalDateTime departureTime,
                             LocalDateTime returnTime,
                             Float price) {
        Flight flight = flightRepository.findById(flightId)
                .orElseThrow(() -> new IllegalStateException("Flight with id " + flightId + " does not exists."));

        if (departureAirportId != null &&
                flight.getDepartureAirport().getAirportId() != departureAirportId &&
                flight.getArrivalAirport().getAirportId() != departureAirportId) {
            Airport depAirport = airportRepository.findById(departureAirportId)
                    .orElseThrow(() -> new IllegalStateException("Airport with id " + departureAirportId + " does not exists."));
            flight.setDepartureAirport(depAirport);
        }

        if (arrivalAirportId != null &&
                flight.getArrivalAirport().getAirportId() != arrivalAirportId &&
                flight.getDepartureAirport().getAirportId() != arrivalAirportId) {
            Airport arrAirport = airportRepository.findById(arrivalAirportId)
                    .orElseThrow(() -> new IllegalStateException("Airport with id " + arrivalAirportId + " does not exists."));
            flight.setDepartureAirport(arrAirport);
        }

        if (departureTime != null &&
                departureTime.isAfter(LocalDateTime.now()) &&
                flight.getDepartureTime().compareTo(departureTime) != 0) {
            flight.setDepartureTime(departureTime);
        } else {
            System.out.println("Error");
        }

        if (returnTime != null &&
                returnTime.isAfter(LocalDateTime.now()) &&
                returnTime.isAfter(flight.getDepartureTime()) &&
                flight.getReturnTime().compareTo(returnTime) != 0) {

            flight.setReturnTime(returnTime);
        }

        if (price != null && price != flight.getPrice() && price > 0) {
            flight.setPrice(price);
        }
    }

    public List<Flight> searchFlights(String departureCity, String arrivalCity, LocalDateTime departureTime, LocalDateTime returnTime) {
        List<Flight> flights;

        if (returnTime == null) {
            flights = flightRepository.findOneWayFlights(departureCity, arrivalCity, departureTime);
        } else {
            flights = flightRepository.findTwoWayFlights(departureCity, arrivalCity, departureTime, returnTime);
            List<Flight> twoWayFlights = new ArrayList<>();
            for(Flight fly: flights){
                twoWayFlights.add(fly);
                Flight flight = new Flight(fly.getArrivalAirport(),fly.getDepartureAirport(),fly.getReturnTime(),null, fly.getPrice());
                twoWayFlights.add(flight);
            }
            flights = twoWayFlights;
        }

        if (flights.isEmpty()) {
            throw new IllegalStateException("There are no appropriate flights for entered information.");
        }
        return flights;
    }
}