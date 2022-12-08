package com.example.aviatales2.repositories;

import com.example.aviatales2.models.Ticket;

import java.util.List;

public interface TicketRepository {
    void save(Ticket ticket);
    void update(Ticket ticket);
    void deleteById(Long id);
    List<Ticket> findByCondition(String condition);
    List<Ticket> findAll();
    List<Ticket> findById(Long id);
}
