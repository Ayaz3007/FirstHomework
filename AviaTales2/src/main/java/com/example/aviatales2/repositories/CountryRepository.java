package com.example.aviatales2.repositories;

import com.example.aviatales2.models.Country;

import java.util.List;

public interface CountryRepository {
    void save(Country country);
    void update(Country country);
    void deleteByName(String name);
    List<Country> findByCondition(String condition);
    List<Country> findAll();
}
