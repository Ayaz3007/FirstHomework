package com.example.aviatales2.repositories.impl;

import com.example.aviatales2.models.Country;
import com.example.aviatales2.repositories.CountryRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class CountryRepositoryImpl implements CountryRepository {
    private final Connection connection;

    private static final String SQL_SAVE_COUNTRY = "insert into country(name) values(?);";
    //SQL
    private static final String SQL_UPDATE_COUNTRY = "update country set name = ?" +
            " where name = ?;";
    private static final String SQL_SELECT_ALL_COUNTRIES = "select * from country";

    private static final String SQL_DELETE_COUNTRY_BY_NAME = "delete from country where name = ?";

    public CountryRepositoryImpl(Connection connection) {
        this.connection = connection;
    }


    private static final Function<ResultSet, Country> countryMapper = row -> {
        try {
            String name = row.getString("name");
            return new Country(name);
        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
    };

    public void save(Country country){
        try (PreparedStatement statement = connection.prepareStatement(SQL_SAVE_COUNTRY)) {

            statement.setString(1, country.getName());
            statement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void update(Country country){
        try(PreparedStatement statement = connection.prepareStatement(SQL_UPDATE_COUNTRY)) {

            statement.setString(1, country.getName());
            statement.setString(2, country.getName());

            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }

    public List<Country> findByCondition(String condition) {

        List<Country> countries = new ArrayList<>();

        try (PreparedStatement statement = connection.prepareStatement(SQL_SELECT_ALL_COUNTRIES + " " + condition)) {

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                countries.add(countryMapper.apply(resultSet));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return countries;
    }

    public void deleteByName(String name){
        try (PreparedStatement statement = connection.prepareStatement(SQL_DELETE_COUNTRY_BY_NAME)) {
            statement.setString(1, name);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public List<Country> findAll(){
        List<Country> countries = new ArrayList<>();

        try (PreparedStatement statement = connection.prepareStatement(SQL_SELECT_ALL_COUNTRIES)) {

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                countries.add(countryMapper.apply(resultSet));
            }
        }
        catch (SQLException e) {
            throw new IllegalArgumentException();
        }
        return countries;
    }
}
