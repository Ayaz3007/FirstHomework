package com.example.aviatales2.repositories;

import com.example.aviatales2.models.Airport;

import java.util.List;

public interface AirportRepository {
    void save(Airport airport);
    void update(Airport airport);
    void deleteByName(String name);
    List<Airport> findByCondition(String condition);
    List<Airport> findAll();
}
