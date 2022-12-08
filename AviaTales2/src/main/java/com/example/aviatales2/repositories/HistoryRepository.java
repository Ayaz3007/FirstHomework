package com.example.aviatales2.repositories;

import com.example.aviatales2.models.FlightHistory;

import java.util.List;

public interface HistoryRepository {
    void save(FlightHistory flightHistory);
    void update(FlightHistory flightHistory);
    void deleteById(Long id);
    List<FlightHistory> findByCondition(String condition);
    List<FlightHistory> findAll();
    void deleteByUserId(Long userId);
    void deleteByTicketId(Long ticketId);
    List<FlightHistory> findByUserId(Long userId);
}
