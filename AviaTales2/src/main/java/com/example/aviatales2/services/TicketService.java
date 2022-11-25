package com.example.aviatales2.services;

import com.example.aviatales2.dto.SearchTicketDto;
import com.example.aviatales2.models.Ticket;

import java.util.List;

public interface TicketService {
    List<Ticket> getAllTickets();
    List<Ticket> searchTickets(SearchTicketDto searchTicketData);
    void generateTickets(SearchTicketDto searchTicketData);
    void deleteById(Long id);
}
