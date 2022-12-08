package com.example.aviatales2.services;

import com.example.aviatales2.dto.SearchTicketDto;
import com.example.aviatales2.dto.TicketDto;
import com.example.aviatales2.models.Ticket;

import java.util.List;

public interface TicketService {
    List<Ticket> getAllTickets();
    List<TicketDto> searchTickets(SearchTicketDto searchTicketData);
    void deleteById(Long id);
    TicketDto getTicketById(Long id);
}
