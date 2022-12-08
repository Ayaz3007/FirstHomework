package com.example.aviatales2.services.impl;

import com.example.aviatales2.dto.SearchTicketDto;
import com.example.aviatales2.dto.TicketDto;
import com.example.aviatales2.dto.converters.TicketToTicketDtoConverter;
import com.example.aviatales2.models.Ticket;
import com.example.aviatales2.repositories.TicketRepository;
import com.example.aviatales2.services.TicketService;

import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;

public class TicketServiceImpl implements TicketService {
    private final TicketRepository ticketRepository;



    public TicketServiceImpl(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }
    @Override
    public List<Ticket> getAllTickets() {
        return ticketRepository.findAll();
    }

    @Override
    public List<TicketDto> searchTickets(SearchTicketDto searchTicketData) {
        String fromCityName = searchTicketData.getFromCityName();
        String toCityName = searchTicketData.getToCityName();
        Date fromDate = searchTicketData.getFromDate();
        Date toDate = searchTicketData.getToDate();

        StringBuilder condition = new StringBuilder("where ");
        condition.append("from_city = ").append("'").append(fromCityName).append("'").append(" and ");

        if(toCityName != null && !toCityName.equals("")) {
            condition.append("to_city = ").append("'").append(toCityName).append("'").append(" and ");
        }

        if(fromDate != null) {
            condition.append("from_date = ").append("'").append(fromDate).append("'").append(" and ");
        }

        if(toDate != null) {
            condition.append("to_date = ").append("'").append(toDate).append("'");
        }
        if (condition.toString().endsWith("and ")) {
            int len = condition.length();
            condition.delete(len - 4, len - 1);
        }
        return ticketRepository.findByCondition(condition.toString())
                .stream()
                .map(TicketToTicketDtoConverter::convert)
                .collect(Collectors.toList());
    }


    @Override
    public void deleteById(Long id) {
        ticketRepository.deleteById(id);
    }

    @Override
    public TicketDto getTicketById(Long id) {
        return TicketToTicketDtoConverter.convert(ticketRepository.findById(id).get(0));
    }
}
