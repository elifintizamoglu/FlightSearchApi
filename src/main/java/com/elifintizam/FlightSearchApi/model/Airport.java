package com.elifintizam.FlightSearchApi.model;

import jakarta.persistence.*;

@Entity
@Table
public class Airport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String city;

    public Airport(){
    }
    public Airport(String city) {
        this.city = city;
    }

    public Long getId() {
        return id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
