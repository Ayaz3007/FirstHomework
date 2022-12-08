package com.example.aviatales2.repositories;

import com.example.aviatales2.models.FlightTime;

import java.sql.Time;
import java.util.List;

public interface FlightTimesRepository {
    void save(FlightTime flightTime);
    void update(FlightTime flightTime);
    void deleteById(Long id);
    List<Time> getAllTimesByAirportId(Long id);
}
