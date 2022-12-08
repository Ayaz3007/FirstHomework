package com.example.aviatales2.repositories.impl;

import com.example.aviatales2.models.Plane;
import com.example.aviatales2.repositories.PlaneRepository;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public class PlaneRepositoryImpl implements PlaneRepository {
    private final Connection connection;
    private static final String SQL_SAVE_PLANE = "insert into plane (" +
            "name, " +
            "place_amount) " +
            "values(?,?);";
    private static final String SQL_FIND_PLANE_BY_ID = "select * from plane where id = ?";
    private static final String SQL_SELECT_ALL_PLANES = "select * from plane";

    public PlaneRepositoryImpl(Connection connection) {
        this.connection = connection;
    }
    private static final Function<ResultSet, Plane> planeMapper = row -> {
        try {
            Long id = row.getLong("id");
            String name = row.getString("name");
            Integer placeAmount = row.getInt("place_amount");

            return new Plane(id, name, placeAmount);
        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
    };
    @Override
    public void save(Plane plane) {
        try(PreparedStatement statement = connection.prepareStatement(SQL_SAVE_PLANE)) {
            statement.setString(1, plane.getName());
            statement.setInt(2, plane.getPlaceAmount());

            statement.executeUpdate();
        } catch (SQLException e) {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public Optional<Plane> findById(Long id) {
        Optional<Plane> plane;
        try(PreparedStatement statement = connection.prepareStatement(SQL_FIND_PLANE_BY_ID)) {
            statement.setLong(1, id);

            plane = Optional.ofNullable(planeMapper.apply(statement.executeQuery()));
        } catch (SQLException e) {
            throw new IllegalArgumentException();
        }
        return plane;
    }

    @Override
    public Optional<String> findNameById(Long id) {
        Optional<String> planeName;
        try(PreparedStatement statement = connection.prepareStatement(SQL_FIND_PLANE_BY_ID)) {
            statement.setLong(1, id);

            ResultSet resultSet = statement.executeQuery();
            resultSet.next();

            planeName = Optional.ofNullable(resultSet.getString("name"));
        } catch (SQLException e) {
            throw new IllegalArgumentException();
        }
        return planeName;
    }

    @Override
    public List<Plane> findAll() {
        List<Plane> planes = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(SQL_SELECT_ALL_PLANES)) {
            ResultSet resultSet = statement.executeQuery();

            while(resultSet.next()) {
                planes.add(planeMapper.apply(resultSet));
            }
        } catch (SQLException e) {
            throw new RuntimeException();
        }
        return planes;
    }
}
