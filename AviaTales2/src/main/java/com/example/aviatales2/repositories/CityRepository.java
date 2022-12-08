package com.example.aviatales2.repositories;

import com.example.aviatales2.models.City;

import java.util.List;

public interface CityRepository {
    void save(City city);
    void update(City city);
    void deleteByName(String name);
    List<City> findByCondition(String condition);
    List<City> findAll();
}
