package com.example.aviatales2.repositories.impl;

import com.example.aviatales2.models.City;
import com.example.aviatales2.repositories.CityRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class CityRepositoryImpl implements CityRepository {
    private final Connection connection;

    private static final String SQL_SAVE_CITY = "insert into city(name, country_name) values(?, ?);";
    //SQL
    private static final String SQL_UPDATE_CITY = "update city set name = ?, country_name = ?" +
            " where name = ?;";
    private static final String SQL_SELECT_ALL_CITIES = "select * from city";

    private static final String SQL_DELETE_CITY_BY_NAME = "delete from city where name = ?";

    public CityRepositoryImpl(Connection connection) {
        this.connection = connection;
    }


    private static final Function<ResultSet, City> cityMapper = row -> {
        try {
            String name = row.getString("name");
            String countryName = row.getString("country_name");

            return new City(name, countryName);
        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
    };

    public void save(City city){
        try (PreparedStatement statement = connection.prepareStatement(SQL_SAVE_CITY)) {

            statement.setString(1, city.getName());
            statement.setString(2, city.getCountryName());
            statement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void update(City city){
        try(PreparedStatement statement = connection.prepareStatement(SQL_UPDATE_CITY)) {

            statement.setString(1, city.getName());
            statement.setString(2, city.getCountryName());
            statement.setString(3, city.getName());

            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }

    public List<City> findByCondition(String condition) {

        List<City> cities = new ArrayList<>();

        try (PreparedStatement statement = connection.prepareStatement(SQL_SELECT_ALL_CITIES + " " + condition)) {

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                cities.add(cityMapper.apply(resultSet));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return cities;
    }

    public void deleteByName(String name){
        try (PreparedStatement statement = connection.prepareStatement(SQL_DELETE_CITY_BY_NAME)) {
            statement.setString(1, name);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public List<City> findAll(){
        List<City> cities = new ArrayList<>();

        try (PreparedStatement statement = connection.prepareStatement(SQL_SELECT_ALL_CITIES)) {

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                cities.add(cityMapper.apply(resultSet));
            }
        }
        catch (SQLException e) {
            throw new IllegalArgumentException();
        }
        return cities;
    }

}
