package com.example.aviatales2.repositories.impl;

import com.example.aviatales2.models.FlightHistory;
import com.example.aviatales2.repositories.HistoryRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class HistoryRepositoryImpl implements HistoryRepository {
    private final Connection connection;

    private static final String SQL_SAVE_CART = "insert into flight_history(username_id, ticket_id) values(?, ?);";
    //SQL
    private static final String SQL_UPDATE_CART = "update flight_history set username_id = ?, ticket_id = ?" +
            " where id = ?;";
    private static final String SQL_SELECT_ALL_CARTS = "select * from flight_history";

    private static final String SQL_DELETE_CART_BY_ID = "delete from flight_history where id = ?";
    private static final String SQL_DELETE_CART_USER_ID = "delete from flight_history where username_id = ?";
    private static final String SQL_DELETE_CART_TICKET_ID = "delete from flight_history where ticket_id = ?";
    private static final String SQL_FIND_BY_USER_ID = "select * from flight_history where username_id = ?";

    public HistoryRepositoryImpl(Connection connection) {
        this.connection = connection;
    }


    private static final Function<ResultSet, FlightHistory> cartMapper = row -> {
        try {
            Long id = row.getLong("id");
            Long usernameId = row.getLong("username_id");
            Long ticket_id = row.getLong("ticket_id");

            return new FlightHistory(id, usernameId, ticket_id);
        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
    };

    public void save(FlightHistory flightHistory){
        try (PreparedStatement statement = connection.prepareStatement(SQL_SAVE_CART)) {

            statement.setLong(1, flightHistory.getUsernameId());
            statement.setLong(2, flightHistory.getTicketId());
            statement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void update(FlightHistory flightHistory){
        try(PreparedStatement statement = connection.prepareStatement(SQL_UPDATE_CART)) {

            statement.setLong(1, flightHistory.getUsernameId());
            statement.setLong(2, flightHistory.getTicketId());
            statement.setLong(3, flightHistory.getId());

            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }

    public List<FlightHistory> findByCondition(String condition) {

        List<FlightHistory> flightHistories = new ArrayList<>();

        try (PreparedStatement statement = connection.prepareStatement(SQL_SELECT_ALL_CARTS + " " + condition)) {

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                flightHistories.add(cartMapper.apply(resultSet));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return flightHistories;
    }

    public void deleteById(Long id){
        try (PreparedStatement statement = connection.prepareStatement(SQL_DELETE_CART_BY_ID)) {
            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public List<FlightHistory> findAll(){
        List<FlightHistory> flightHistories = new ArrayList<>();

        try (PreparedStatement statement = connection.prepareStatement(SQL_SELECT_ALL_CARTS)) {

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                flightHistories.add(cartMapper.apply(resultSet));
            }
        }
        catch (SQLException e) {
            throw new IllegalArgumentException();
        }
        return flightHistories;
    }

    @Override
    public void deleteByUserId(Long userId) {
        try (PreparedStatement statement = connection.prepareStatement(SQL_DELETE_CART_USER_ID)) {
            statement.setLong(1, userId);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Override
    public void deleteByTicketId(Long ticketId) {
        try (PreparedStatement statement = connection.prepareStatement(SQL_DELETE_CART_TICKET_ID)) {
            statement.setLong(1, ticketId);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Override
    public List<FlightHistory> findByUserId(Long userId) {
        List<FlightHistory> flightHistories = new ArrayList<>();

        try (PreparedStatement statement = connection.prepareStatement(SQL_FIND_BY_USER_ID)) {

            statement.setLong(1, userId);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                flightHistories.add(cartMapper.apply(resultSet));
            }
        }
        catch (SQLException e) {
            throw new IllegalArgumentException();
        }
        return flightHistories;
    }
}
