package com.elifintizam.FlightSearchApi.repository;

import com.elifintizam.FlightSearchApi.model.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface FlightRepository extends JpaRepository<Flight, Long> {

    @Query("SELECT f FROM Flight f WHERE f.departureAirport.city = :departureCity " +
            "AND f.arrivalAirport.city = :arrivalCity " +
            "AND f.departureTime >= :startOfDepartureDay " +
            "AND f.departureTime <= :endOfDepartureDay")
    List<Flight> findOneWayFlights(String departureCity,
                                   String arrivalCity,
                                   LocalDateTime startOfDepartureDay,
                                   LocalDateTime endOfDepartureDay);

    @Query("SELECT f FROM Flight f WHERE f.departureAirport.city = :departureCity " +
            "AND f.arrivalAirport.city = :arrivalCity " +
            "AND f.departureTime >= :startOfDepartureDay " +
            "AND f.departureTime <= :endOfDepartureDay " +
            "AND f.returnTime >= :startOfReturnDay " +
            "AND f.returnTime <= :endOfReturnDay")
    List<Flight> findTwoWayFlights(String departureCity,
                                   String arrivalCity,
                                   LocalDateTime startOfDepartureDay,
                                   LocalDateTime endOfDepartureDay,
                                   LocalDateTime startOfReturnDay,
                                   LocalDateTime endOfReturnDay);
}
