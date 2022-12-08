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
            "from_date, " +
            "to_date, " +
            "seat_place, " +
            "from_airport_id, " +
            "to_airport_id, " +
            "from_time, " +
            "to_time, " +
            "from_plane, " +
            "to_plane," +
            "from_city," +
            "to_city" +
            ") values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
    //SQL
    private static final String SQL_UPDATE_TICKET = "update ticket set " +
            "price = ?, " +
            "from_date = ?, " +
            "to_date = ?, " +
            "seat_place = ?," +
            "from_airport_id = ?," +
            "to_airport_id = ?," +
            "from_time = ?," +
            "to_time = ?," +
            "from_plane = ?," +
            "to_plane = ?," +
            "from_city = ?," +
            "to_city = ?" +
            "where id = ?;";
    private static final String SQL_SELECT_ALL_TICKETS = "select * from ticket";

    private static final String SQL_DELETE_TICKET_BY_ID = "delete from ticket where id = ?";
    private static final String SQL_FIND_BY_ID = "select * from ticket where id = ?";


    public TicketRepositoryImpl(Connection connection) {
        this.connection = connection;
    }

    private static final Function<ResultSet, Ticket> ticketMapper = row -> {
        try {
            Long id = row.getLong("id");
            Integer price = row.getInt("price");
            Date fromDate = row.getDate("from_date");
            Date toDate = row.getDate("to_date");
            Integer seatPlace = row.getInt("seat_place");
            Long fromAirportName = row.getLong("from_airport_id");
            Long toAirportName = row.getLong("to_airport_id");
            Time fromTime = row.getTime("from_time");
            Time toTime = row.getTime("to_time");
            Long fromPlane = row.getLong("from_plane");
            Long toPlane = row.getLong("to_plane");
            String fromCity = row.getString("from_city");
            String toCity = row.getString("to_city");

            return new Ticket(id, price, fromDate, toDate,
                    seatPlace, fromAirportName, toAirportName, fromTime, toTime, fromPlane, toPlane, fromCity, toCity);
        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
    };

    public void save(Ticket ticket){
        try(PreparedStatement statement = connection.prepareStatement(SQL_SAVE_TICKET,
                Statement.RETURN_GENERATED_KEYS)) {
            statement.setInt(1, ticket.getPrice());
            statement.setDate(2, ticket.getFromDate());
            statement.setDate(3, ticket.getToDate());
            statement.setInt(4, ticket.getSeatPlace());
            statement.setLong(5, ticket.getFromAirportId());
            statement.setLong(6, ticket.getToAirportId());
            statement.setTime(7, ticket.getFromTime());
            statement.setTime(8, ticket.getToTime());
            statement.setLong(9, ticket.getFromPlane());
            statement.setLong(10, ticket.getToPlane());
            statement.setString(11, ticket.getFromCity());
            statement.setString(12, ticket.getToCity());

            statement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void update(Ticket ticket){
        try(PreparedStatement statement = connection.prepareStatement(SQL_UPDATE_TICKET)) {

            statement.setInt(1, ticket.getPrice());
            statement.setDate(2, ticket.getFromDate());
            statement.setDate(3, ticket.getToDate());
            statement.setInt(4, ticket.getSeatPlace());
            statement.setLong(5, ticket.getFromAirportId());
            statement.setLong(6, ticket.getToAirportId());
            statement.setTime(7, ticket.getFromTime());
            statement.setTime(8, ticket.getToTime());
            statement.setLong(9, ticket.getFromPlane());
            statement.setLong(10, ticket.getToPlane());
            statement.setString(11, ticket.getFromCity());
            statement.setString(12, ticket.getToCity());
            statement.setLong(13, ticket.getId());

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

    @Override
    public List<Ticket> findById(Long id) {
        List<Ticket> tickets = new ArrayList<>();

        try (PreparedStatement statement = connection.prepareStatement(SQL_FIND_BY_ID)) {
            statement.setLong(1, id);
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
