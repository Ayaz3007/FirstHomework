package com.example.aviatales2.repositories;

import com.example.aviatales2.models.PlaneToAirport;

import java.util.List;

public interface PlaneToAirportRepository {
    void save(PlaneToAirport planeToAirport);
    void deleteByAirportId(Long id);
    List<Long> getAllPlanesByAirportId(Long id);
}
