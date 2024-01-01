package com.elifintizam.FlightSearchApi.exception;

public class FlightNotFoundException extends RuntimeException{
    public FlightNotFoundException(Long id) {
        super("Flight with id " + id + " could not found.");
    }
}
