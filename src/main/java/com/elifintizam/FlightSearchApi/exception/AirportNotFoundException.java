package com.elifintizam.FlightSearchApi.exception;

public class AirportNotFoundException extends RuntimeException{
    public AirportNotFoundException(Long id) {
        super("Airport with id " + id + " could not found.");
    }
}
