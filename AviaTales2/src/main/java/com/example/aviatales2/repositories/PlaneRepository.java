package com.example.aviatales2.repositories;

import com.example.aviatales2.models.Plane;

import java.util.List;
import java.util.Optional;

public interface PlaneRepository {
    void save(Plane plane);
    void deleteById(Long id);
    Optional<Plane> findById(Long id);
    Optional<String> findNameById(Long id);
    List<Plane> findAll();
}
