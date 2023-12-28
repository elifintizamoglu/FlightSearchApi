package com.elifintizam.FlightSearchApi.model;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table
public class Flight {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String departureAirport;
    private String destinationAirport;
    private LocalDateTime departureTime;
    private LocalDateTime returnTime;
    private float price;

    public Flight(String departureAirport,
                  String destinationAirport,
                  LocalDateTime departureTime,
                  LocalDateTime returnTime,
                  float price) {
        this.departureAirport = departureAirport;
        this.destinationAirport = destinationAirport;
        this.departureTime = departureTime;
        this.returnTime = returnTime;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public String getDepartureAirport() {
        return departureAirport;
    }

    public void setDepartureAirport(String departureAirport) {
        this.departureAirport = departureAirport;
    }

    public String getDestinationAirport() {
        return destinationAirport;
    }

    public void setDestinationAirport(String destinationAirport) {
        this.destinationAirport = destinationAirport;
    }

    public LocalDateTime getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(LocalDateTime departureTime) {
        this.departureTime = departureTime;
    }

    public LocalDateTime getReturnTime() {
        return returnTime;
    }

    public void setReturnTime(LocalDateTime returnTime) {
        this.returnTime = returnTime;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
}
