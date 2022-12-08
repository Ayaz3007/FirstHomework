package com.example.aviatales2.repositories.impl;

import com.example.aviatales2.models.PlaneToAirport;
import com.example.aviatales2.repositories.PlaneToAirportRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PlaneToAirportRepositoryImpl implements PlaneToAirportRepository {
    private final Connection connection;
    private static final String SQL_SAVE = "insert into plane_to_airport (" +
            "airport_id, " +
            "plane_id) " +
            "values(?,?);";
    private static final String SQL_SELECT_PLANES_BY_AIRPORT_ID = "select * from plane_to_airport where airport_id = ?";
    public PlaneToAirportRepositoryImpl(Connection connection) {
        this.connection = connection;
    }
    @Override
    public void save(PlaneToAirport planeToAirport) {
        try(PreparedStatement statement = connection.prepareStatement(SQL_SAVE)) {
            statement.setLong(1, planeToAirport.getAirportId());
            statement.setLong(2, planeToAirport.getPlaneId());

            statement.executeUpdate();
        } catch (SQLException e) {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public void deleteByAirportId(Long id) {

    }

    @Override
    public List<Long> getAllPlanesByAirportId(Long id) {
        List<Long> planeIds = new ArrayList<>();

        try (PreparedStatement statement = connection.prepareStatement(SQL_SELECT_PLANES_BY_AIRPORT_ID)) {

            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                planeIds.add(resultSet.getLong("plane_id"));
            }
        }
        catch (SQLException e) {
            throw new IllegalArgumentException();
        }
        return planeIds;
    }
}
