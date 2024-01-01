package com.elifintizam.FlightSearchApi.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;

@Entity
@Table
public class Airport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long airportId;
    @NotEmpty(message = "City can not be empty.")
    private String city;

    public Airport(){}

    public Airport(String city) {
        this.city = city;
    }

    public Long getAirportId() {
        return airportId;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}