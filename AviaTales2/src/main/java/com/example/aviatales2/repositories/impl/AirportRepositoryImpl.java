package com.example.aviatales2.repositories.impl;

import com.example.aviatales2.models.Airport;
import com.example.aviatales2.repositories.AirportRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class AirportRepositoryImpl implements AirportRepository {
    private final Connection connection;

    private static final String SQL_SAVE_AIRPORT = "insert into airport(name, city, country) values(?, ?, ?);";
    //SQL
    private static final String SQL_UPDATE_AIRPORT = "update airport set name = ?, city = ?, country = ?" +
            " where name = ?;";
    private static final String SQL_SELECT_ALL_AIRPORTS = "select * from airport";

    private static final String SQL_DELETE_AIRPORT_BY_NAME = "delete from airport where name = ?";

    public AirportRepositoryImpl(Connection connection) {
        this.connection = connection;
    }


    private static final Function<ResultSet, Airport> airportMapper = row -> {
        try {
            String name = row.getString("name");
            String city = row.getString("city");
            String country = row.getString("country");

            return new Airport(name, city, country);
        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
    };

    public void save(Airport airport){
        try (PreparedStatement statement = connection.prepareStatement(SQL_SAVE_AIRPORT)) {

            statement.setString(1, airport.getName());
            statement.setString(2, airport.getCityName());
            statement.setString(3, airport.getCountryName());
            statement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void update(Airport airport){
        try(PreparedStatement statement = connection.prepareStatement(SQL_UPDATE_AIRPORT)) {

            statement.setString(1, airport.getName());
            statement.setString(2, airport.getCityName());
            statement.setString(3, airport.getCountryName());
            statement.setString(3, airport.getName());

            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }

    public List<Airport> findByCondition(String condition) {

        List<Airport> airports = new ArrayList<>();

        try (PreparedStatement statement = connection.prepareStatement(SQL_SELECT_ALL_AIRPORTS + " " + condition)) {

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                airports.add(airportMapper.apply(resultSet));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return airports;
    }

    public void deleteByName(String name){
        try (PreparedStatement statement = connection.prepareStatement(SQL_DELETE_AIRPORT_BY_NAME)) {
            statement.setString(1, name);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public List<Airport> findAll(){
        List<Airport> airports = new ArrayList<>();

        try (PreparedStatement statement = connection.prepareStatement(SQL_SELECT_ALL_AIRPORTS)) {

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                airports.add(airportMapper.apply(resultSet));
            }
        }
        catch (SQLException e) {
            throw new IllegalArgumentException();
        }
        return airports;
    }

}
