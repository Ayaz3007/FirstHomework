package com.example.aviatales2.repositories.impl;

import com.example.aviatales2.models.Ticket;
import com.example.aviatales2.repositories.TicketRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class TicketRepositoryImpl implements TicketRepository {
    private final Connection connection;
    //SQL
    private static final String SQL_SAVE_TICKET = "insert into ticket(" +
            "price, " +
            "airline, " +
            "from_date, " +
            "to_date, " +
            "from_departure_time, " +
            "from_landing_time, " +
            "to_departure_time, " +
            "to_landing_time, " +
            "seat_place, " +
            "airport_name, " +
            "from_city_name, " +
            "to_city_name, " +
            "baggage) values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
    //SQL
    private static final String SQL_UPDATE_TICKET = "update ticket set " +
            "price = ?, " +
            "airline = ?," +
            "from_date = ?, " +
            "to_date = ?, " +
            "from_departure_time = ?," +
            "from_landing_time = ?," +
            "to_departure_time = ?," +
            "to_landing_time = ?," +
            "seat_place = ?," +
            "airport_name = ?," +
            "from_city_name = ?," +
            "to_city_name = ?," +
            "baggage = ? " +
            "where id = ?;";
    private static final String SQL_SELECT_ALL_TICKETS = "select * from ticket";

    private static final String SQL_DELETE_TICKET_BY_ID = "delete from ticket where id = ?";


    public TicketRepositoryImpl(Connection connection) {
        this.connection = connection;
    }

    private static final Function<ResultSet, Ticket> ticketMapper = row -> {
        try {
            Long id = row.getLong("id");
            Integer price = row.getInt("price");
            String airline = row.getString("airline");
            Date fromDate = row.getDate("from_date");
            Date toDate = row.getDate("to_date");
            Time fromDepartureTime = row.getTime("from_departure_time");
            Time fromLandingTime = row.getTime("from_landing_time");
            Time toDepartureTime = row.getTime("to_departure_time");
            Time toLandingTime = row.getTime("to_landing_time");
            Integer seatPlace = row.getInt("seat_place");
            String airportName = row.getString("airport_name");
            String fromCityName = row.getString("from_city_name");
            String toCityName = row.getString("to_city_name");
            Boolean baggage = row.getBoolean("baggage");


            return new Ticket(id, price, airline, fromDate, toDate, fromDepartureTime, fromLandingTime, toDepartureTime,
                    toLandingTime, seatPlace, airportName, fromCityName, toCityName,  baggage);
        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
    };

    public void save(Ticket ticket){
        try(PreparedStatement statement = connection.prepareStatement(SQL_SAVE_TICKET,
                Statement.RETURN_GENERATED_KEYS)) {
            statement.setInt(1, ticket.getPrice());
            statement.setString(2, ticket.getAirline());
            statement.setDate(3, ticket.getFromDate());
            statement.setDate(4, ticket.getToDate());
            statement.setTime(5, ticket.getFromDepartureTime());
            statement.setTime(6, ticket.getFromLandingTime());
            statement.setTime(7, ticket.getToDepartureTime());
            statement.setTime(8, ticket.getToLandingTime());
            statement.setInt(9, ticket.getSeatPlace());
            statement.setString(10, ticket.getAirportName());
            statement.setString(11, ticket.getFromCityName());
            statement.setString(12, ticket.getToCityName());
            statement.setBoolean(13, ticket.getBaggage());

            statement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void update(Ticket ticket){
        try(PreparedStatement statement = connection.prepareStatement(SQL_UPDATE_TICKET)) {

            statement.setInt(1, ticket.getPrice());
            statement.setString(2, ticket.getAirline());
            statement.setDate(3, ticket.getFromDate());
            statement.setDate(4, ticket.getToDate());
            statement.setTime(5, ticket.getFromDepartureTime());
            statement.setTime(6, ticket.getFromLandingTime());
            statement.setTime(7, ticket.getToDepartureTime());
            statement.setTime(8, ticket.getToLandingTime());
            statement.setInt(9, ticket.getSeatPlace());
            statement.setString(10, ticket.getAirportName());
            statement.setString(11, ticket.getFromCityName());
            statement.setString(12, ticket.getToCityName());
            statement.setBoolean(13, ticket.getBaggage());
            statement.setLong(14, ticket.getId());

            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }

    public List<Ticket> findByCondition(String condition) {

        List<Ticket> tickets = new ArrayList<>();

        try (PreparedStatement statement = connection.prepareStatement(SQL_SELECT_ALL_TICKETS + " " + condition)) {

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                tickets.add(ticketMapper.apply(resultSet));
            }

        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }

        return tickets;
    }

    public void deleteById(Long id){
        try (PreparedStatement statement = connection.prepareStatement(SQL_DELETE_TICKET_BY_ID)) {
            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public List<Ticket> findAll(){
        List<Ticket> tickets = new ArrayList<>();

        try (PreparedStatement statement = connection.prepareStatement(SQL_SELECT_ALL_TICKETS)) {

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                tickets.add(ticketMapper.apply(resultSet));
            }
        }
        catch (SQLException e) {
            throw new IllegalArgumentException();
        }
        return tickets;
    }
}
