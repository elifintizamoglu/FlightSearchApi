package com.elifintizam.FlightSearchApi.repository;

import com.elifintizam.FlightSearchApi.model.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface FlightRepository extends JpaRepository<Flight,Long> {

    @Query("SELECT f FROM Flight f WHERE f.departureAirport.city = :departureCity AND f.arrivalAirport.city = :arrivalCity AND f.departureTime = :departureTime")
    List<Flight> findOneWayFlights(String departureCity, String arrivalCity, LocalDateTime departureTime);

    @Query("SELECT f FROM Flight f WHERE f.departureAirport.city = :departureCity AND f.arrivalAirport.city = :arrivalCity AND f.departureTime = :departureTime AND f.returnTime = :returnTime")
    List<Flight> findTwoWayFlights(String departureCity, String arrivalCity, LocalDateTime departureTime, LocalDateTime returnTime);
}
