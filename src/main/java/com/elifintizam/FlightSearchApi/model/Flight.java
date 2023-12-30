package com.elifintizam.FlightSearchApi.model;
import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDateTime;

@Entity
@Table
public class Flight {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long flightId;
    @ManyToOne
    @JoinColumn(name = "departureAirportId")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Airport departureAirport;
    @ManyToOne
    @JoinColumn(name = "arrivalAirportId")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Airport arrivalAirport;
    private LocalDateTime departureTime;
    private LocalDateTime returnTime;
    private Float price;

    public Flight(){}

    public Flight(Airport departureAirport,
                  Airport arrivalAirport,
                  LocalDateTime departureTime,
                  LocalDateTime returnTime,
                  Float price) {
        this.departureAirport = departureAirport;
        this.arrivalAirport = arrivalAirport;
        this.departureTime = departureTime;
        this.returnTime = returnTime;
        this.price = price;
    }

    public Long getId() {
        return flightId;
    }

    public Airport getDepartureAirport() {
        return departureAirport;
    }

    public void setDepartureAirport(Airport departureAirport) {
        this.departureAirport = departureAirport;
    }

    public Airport getArrivalAirport() {
        return arrivalAirport;
    }

    public void setArrivalAirport(Airport arrivalAirport) {
        this.arrivalAirport = arrivalAirport;
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

    public void setPrice(Float price) {
        this.price = price;
    }
}