package com.elifintizam.FlightSearchApi.service;

import com.elifintizam.FlightSearchApi.exception.AirportNotFoundException;
import com.elifintizam.FlightSearchApi.exception.FlightNotFoundException;
import com.elifintizam.FlightSearchApi.model.Airport;
import com.elifintizam.FlightSearchApi.model.Flight;
import com.elifintizam.FlightSearchApi.repository.AirportRepository;
import com.elifintizam.FlightSearchApi.repository.FlightRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
                new FlightNotFoundException(flightId));
    }

    /*
     * To be able to add a new flight,
     * entered airports must exist in database,
     * departureAirport and arrivalAirport must be different,
     * departureTime must be in the future,
     * and returnTime must be after the departureTime.
     */
    public void addFlight(Flight flight) {
        if (airportRepository.existsById(flight.getDepartureAirport().getAirportId()) &&
                airportRepository.existsById(flight.getArrivalAirport().getAirportId()) &&
                !Objects.equals(flight.getDepartureAirport().getAirportId(), flight.getArrivalAirport().getAirportId()) &&
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
            throw new FlightNotFoundException(flightId);
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
                .orElseThrow(() -> new FlightNotFoundException(flightId));

        if (departureAirportId != null &&
                !Objects.equals(flight.getDepartureAirport().getAirportId(), departureAirportId) &&
                !Objects.equals(flight.getArrivalAirport().getAirportId(), departureAirportId)) {
            Airport depAirport = airportRepository.findById(departureAirportId)
                    .orElseThrow(() -> new AirportNotFoundException(departureAirportId));
            flight.setDepartureAirport(depAirport);
        }

        if (arrivalAirportId != null &&
                !Objects.equals(flight.getArrivalAirport().getAirportId(), arrivalAirportId) &&
                !Objects.equals(flight.getDepartureAirport().getAirportId(), arrivalAirportId)) {
            Airport arrAirport = airportRepository.findById(arrivalAirportId)
                    .orElseThrow(() -> new AirportNotFoundException(departureAirportId));
            flight.setDepartureAirport(arrAirport);
        }

        if (departureTime != null &&
                departureTime.isAfter(LocalDateTime.now()) &&
                !flight.getDepartureTime().isEqual(departureTime)) {
            flight.setDepartureTime(departureTime);
        }

        if (returnTime != null &&
                returnTime.isAfter(LocalDateTime.now()) &&
                returnTime.isAfter(flight.getDepartureTime()) &&
                !flight.getReturnTime().isEqual(returnTime)) {

            flight.setReturnTime(returnTime);
        }

        if (price != null && price != flight.getPrice() && price > 0) {
            flight.setPrice(price);
        }
    }

    /*
     * In this method, flights are searched according to departureCity, arrivalCity, and departureTime.
     * A list of flights returned.
     * If user enters returnTime, two-way flights are listed.
     *
     * Flight departureTime and returnTime are LocalDateTime in database,
     * but departureTime and returnTime are taken as LocalDate from user to make it easier for them.
     * To be able to compare two types and bring appropriate flights, LocalDate data is converted to LocalDateTime.
     */
    public List<Flight> searchFlights(String departureCity, String arrivalCity, LocalDate departureTime, LocalDate returnTime) {
        List<Flight> flights;
        LocalDateTime startOfDepartureDay = departureTime.atStartOfDay();
        LocalDateTime endOfDepartureDay = LocalDateTime.of(departureTime, LocalTime.MAX);
        LocalDateTime startOfReturnDay, endOfReturnDay;

        if (returnTime == null) {
            flights = flightRepository.findOneWayFlights(departureCity, arrivalCity, startOfDepartureDay, endOfDepartureDay);
        } else {
            startOfReturnDay = returnTime.atStartOfDay();
            endOfReturnDay = LocalDateTime.of(returnTime, LocalTime.MAX);
            flights = flightRepository.findTwoWayFlights(departureCity, arrivalCity, startOfDepartureDay, endOfDepartureDay, startOfReturnDay, endOfReturnDay);
            List<Flight> twoWayFlights = new ArrayList<>();
            for (Flight fly : flights) {
                twoWayFlights.add(fly);
                Flight flight = new Flight(fly.getArrivalAirport(), fly.getDepartureAirport(), fly.getReturnTime(), null, fly.getPrice());
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