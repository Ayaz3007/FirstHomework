package com.example.aviatales2.repositories.impl;

import com.example.aviatales2.models.FlightTime;
import com.example.aviatales2.repositories.FlightTimesRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FlightTimesRepositoryImpl implements FlightTimesRepository {
    private final Connection connection;

    private static final String SQL_SAVE_FLIGHT_TIME = "insert into flight_times (" +
            "airport_id, " +
            "flight_time) " +
            "values(?,?);";
    private static final String SQL_SELECT_BY_AIRPORT_ID = "select * from flight_times where airport_id = ?";

    public FlightTimesRepositoryImpl(Connection connection) {
        this.connection = connection;
    }
    @Override
    public void save(FlightTime flightTime) {
        try(PreparedStatement statement = connection.prepareStatement(SQL_SAVE_FLIGHT_TIME)) {
            statement.setLong(1, flightTime.getAirportId());
            statement.setTime(2, flightTime.getFlightTime());

            statement.executeUpdate();
        } catch (SQLException e) {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public void update(FlightTime flightTime) {

    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public List<Time> getAllTimesByAirportId(Long id) {
        List<Time> times = new ArrayList<>();

        try (PreparedStatement statement = connection.prepareStatement(SQL_SELECT_BY_AIRPORT_ID)) {

            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                times.add(resultSet.getTime("flight_time"));
            }
        }
        catch (SQLException e) {
            throw new IllegalArgumentException();
        }
        return times;
    }
}
