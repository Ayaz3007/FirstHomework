package com.example.aviatales2.services.impl;

import com.example.aviatales2.dto.SearchTicketDto;
import com.example.aviatales2.models.Ticket;
import com.example.aviatales2.repositories.TicketRepository;
import com.example.aviatales2.services.TicketService;

import java.sql.Date;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

public class TicketServiceImpl implements TicketService {
    private final TicketRepository ticketRepository;

    private static int randBetween(int start, int end) {
        return (int)(Math.random()*((end-start)+1))+start;
    }

    private static Time getTime() {
        int hours = randBetween(1, 23);
        int minutes = randBetween(1, 59);

        String stringHours = hours < 10 ? "0" + hours : "" + hours;
        String stringMinutes = minutes < 10 ? "0" + minutes : "" + minutes;

        java.util.Date time = null;
        try {
            time = new SimpleDateFormat("hh:mm:ss")
                    .parse(stringHours + ":" + stringMinutes + ":00");
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        return new Time(time.getTime());
    }
    public TicketServiceImpl(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }
    @Override
    public List<Ticket> getAllTickets() {
        return ticketRepository.findAll();
    }

    @Override
    public List<Ticket> searchTickets(SearchTicketDto searchTicketData) {
        String fromCityName = searchTicketData.getFromCityName();
        String toCityName = searchTicketData.getToCityName();
        Date fromDate = searchTicketData.getFromDate();
        Date toDate = searchTicketData.getToDate();

        StringBuilder condition = new StringBuilder("where ");
        condition.append("from_city_name = ").append("'").append(fromCityName).append("'").append(" and ");

        if(toCityName != null && !toCityName.equals("")) {
            condition.append("to_city_name = ").append("'").append(toCityName).append("'").append(" and ");
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
        return ticketRepository.findByCondition(condition.toString());
    }

    @Override
    public void generateTickets(SearchTicketDto searchTicketData) {
        for(int i = 0; i < 5; i++) {
            Time to = getTime();
            Time from = getTime();
            if(from.getTime() > to.getTime()) {
                Time buf = to;
                to = from;
                from = buf;
            }
            ticketRepository.save(
                    Ticket.builder()
                            .price(5000)
                            .airline("Airline")
                            .fromDate(searchTicketData.getFromDate())
                            .toDate(searchTicketData.getToDate())
                            .fromDepartureTime(from)
                            .fromLandingTime(to)
                            .toDepartureTime(from)
                            .toLandingTime(to)
                            .seatPlace(randBetween(1, 300))
                            .airportName("Airport")
                            .fromCityName(searchTicketData.getFromCityName())
                            .toCityName(searchTicketData.getToCityName())
                            .baggage(false)
                            .build()
            );
        }
    }

    @Override
    public void deleteById(Long id) {
        ticketRepository.deleteById(id);
    }
}
