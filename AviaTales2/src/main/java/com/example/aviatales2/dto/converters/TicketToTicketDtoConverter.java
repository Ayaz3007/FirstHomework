package com.example.aviatales2.dto.converters;

import com.example.aviatales2.connection.PostgresConnectionProvider;
import com.example.aviatales2.dto.TicketDto;
import com.example.aviatales2.models.Ticket;
import com.example.aviatales2.repositories.AirportRepository;
import com.example.aviatales2.repositories.PlaneRepository;
import com.example.aviatales2.repositories.impl.AirportRepositoryImpl;
import com.example.aviatales2.repositories.impl.PlaneRepositoryImpl;

import java.sql.Connection;

public class TicketToTicketDtoConverter {
    private static final Connection connection = PostgresConnectionProvider.getConnection();
    private static final AirportRepository airportRepository = new AirportRepositoryImpl(connection);

    private static final PlaneRepository planeRepository = new PlaneRepositoryImpl(connection);


    public static TicketDto convert(Ticket ticket) {
        String fromAirportName = airportRepository.getAirportNameById(ticket.getFromAirportId());
        String toAirportName = airportRepository.getAirportNameById(ticket.getToAirportId());
        String fromPlane = planeRepository.findNameById(ticket.getFromPlane()).orElse("");
        String toPlane = planeRepository.findNameById(ticket.getToPlane()).orElse("");

        return TicketDto.builder()
                .id(ticket.getId())
                .price(ticket.getPrice())
                .fromDate(ticket.getFromDate())
                .toDate(ticket.getToDate())
                .fromFlightTime(ticket.getFromTime())
                .toFlightTime(ticket.getToTime())
                .seatPlace(ticket.getSeatPlace())
                .fromCityName(ticket.getFromCity())
                .toCityName(ticket.getToCity())
                .fromAirportName(fromAirportName)
                .toAirportName(toAirportName)
                .fromPlaneName(fromPlane)
                .toPlaneName(toPlane)
                .build();
    }
}
