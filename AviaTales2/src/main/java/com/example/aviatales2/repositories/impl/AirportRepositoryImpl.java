package com.example.aviatales2.repositories.impl;

import com.example.aviatales2.models.Airport;
import com.example.aviatales2.repositories.AirportRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class AirportRepositoryImpl implements AirportRepository {
    private final Connection connection;

    private static final String SQL_SAVE_AIRPORT = "insert into airport(name, city) values(?, ?);";
    //SQL
    private static final String SQL_UPDATE_AIRPORT = "update airport set name = ?, city = ?" +
            " where id = ?;";
    private static final String SQL_SELECT_ALL_AIRPORTS = "select * from airport";

    private static final String SQL_DELETE_AIRPORT_BY_NAME = "delete from airport where name = ?";
    private static final String SQL_SELECT_CITY_NAME_BY_ID = "select city from airport where id = ?";
    private static final String SQL_SELECT_AIRPORT_NAME_BY_ID = "select name from airport where id = ?";
    private static final String SQL_SELECT_BY_CITY_NAME = "select * from airport where city = ?";
    public AirportRepositoryImpl(Connection connection) {
        this.connection = connection;
    }


    private static final Function<ResultSet, Airport> airportMapper = row -> {
        try {
            Long id = row.getLong("id");
            String name = row.getString("name");
            String city = row.getString("city");

            return new Airport(id, name, city);
        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
    };

    public void save(Airport airport){
        try (PreparedStatement statement = connection.prepareStatement(SQL_SAVE_AIRPORT)) {

            statement.setString(1, airport.getName());
            statement.setString(2, airport.getCityName());
            statement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void update(Airport airport){
        try(PreparedStatement statement = connection.prepareStatement(SQL_UPDATE_AIRPORT)) {

            statement.setString(1, airport.getName());
            statement.setString(2, airport.getCityName());
            statement.setLong(3, airport.getId());

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

    @Override
    public String getCityNameById(Long id) {
        try(PreparedStatement statement = connection.prepareStatement(SQL_SELECT_CITY_NAME_BY_ID)) {
            statement.setLong(1, id);

            return statement.executeQuery().getString("city");
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }

    @Override
    public String getAirportNameById(Long id) {
        try(PreparedStatement statement = connection.prepareStatement(SQL_SELECT_AIRPORT_NAME_BY_ID)) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            return resultSet.getString("name");
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }

    @Override
    public List<Airport> findAllByCityName(String name) {
        List<Airport> airports = new ArrayList<>();
        try(PreparedStatement statement = connection.prepareStatement(SQL_SELECT_BY_CITY_NAME)) {
            statement.setString(1, name);

            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()) {
                airports.add(airportMapper.apply(resultSet));
            }
        } catch (SQLException e) {
            throw new RuntimeException();
        }
        return airports;
    }

}
