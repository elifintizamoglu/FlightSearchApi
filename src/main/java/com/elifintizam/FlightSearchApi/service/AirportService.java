package com.elifintizam.FlightSearchApi.service;

import com.elifintizam.FlightSearchApi.model.Airport;
import com.elifintizam.FlightSearchApi.repository.AirportRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class AirportService {

    private final AirportRepository airportRepository;

    @Autowired
    public AirportService(AirportRepository airportRepository) {
        this.airportRepository = airportRepository;
    }

    public List<Airport> getAirports() {
        return airportRepository.findAll();
    }

    public Airport getAirport(Long airportId) {
        return airportRepository.findById(airportId)
                .orElseThrow(() -> new IllegalStateException("Airport with id " + airportId + " does not found."));
    }

    public void addAirport(Airport airport) {
        if (airport.getCity() == null) {
            throw new IllegalStateException("City area should not be empty.");
        }
        airportRepository.save(airport);
    }

    public void deleteAirport(Long airportId) {
        boolean exists = airportRepository.existsById(airportId);
        if (!exists) {
            throw new IllegalStateException("Airport with id " + airportId + " does not found.");
        }
        airportRepository.deleteById(airportId);
    }

    @Transactional
    public void updateAirport(Long airportId, String city) {
        Airport airport = airportRepository.findById(airportId)
                .orElseThrow(() -> new IllegalStateException("Airport with id " + airportId + " does not found."));

        if (city != null && !Objects.equals(airport.getCity(), city)) {
            airport.setCity(city);
        }
    }
}
