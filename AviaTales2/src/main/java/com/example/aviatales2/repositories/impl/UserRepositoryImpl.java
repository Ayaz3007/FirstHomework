package com.example.aviatales2.repositories.impl;

import com.example.aviatales2.models.User;
import com.example.aviatales2.repositories.UserRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class UserRepositoryImpl implements UserRepository {

    private final Connection connection;
    //SQL
    private static final String SQL_SAVE_USER = "insert into at_user(username, " +
            "email, password, phone, is_admin) values(?, ?, ?, ?, ?);";
    //SQL
    private static final String SQL_UPDATE_USER = "update at_user set username = ?, email = ?," +
            "password = ?, phone = ?, is_admin = ? where id = ?;";
    private static final String SQL_SELECT_ALL_USERS = "select * from at_user";

    private static final String SQL_DELETE_USER_BY_ID = "delete from at_user where id = ?";

    public UserRepositoryImpl(Connection connection) {
        this.connection = connection;
    }

    private static final Function<ResultSet, User> userMapper = row -> {
        try {
            Long id = row.getLong("id");
            String name = row.getString("username");
            String email = row.getString("email");
            String password = row.getString("password");
            String phone = row.getString("phone");
            Boolean isAdmin = row.getBoolean("is_admin");

            return new User(id, name, email, password,  phone, isAdmin);
        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
    };

    public void save(User user){
        try(PreparedStatement statement = connection.prepareStatement(SQL_SAVE_USER,
                    Statement.RETURN_GENERATED_KEYS)) {

            statement.setString(1, user.getName());
            statement.setString(2, user.getEmail());
            statement.setString(3, user.getPassword());
            statement.setString(4, user.getPhone());
            statement.setBoolean(5, user.getIsAdmin());
            statement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void update(User user){
        try(PreparedStatement statement = connection.prepareStatement(SQL_UPDATE_USER)) {

            statement.setString(1, user.getName());
            statement.setString(2, user.getEmail());
            statement.setString(3, user.getPassword());
            statement.setString(4, user.getPhone());
            statement.setBoolean(5, user.getIsAdmin());
            statement.setLong(6, user.getId());

            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }

    public List<User> findByCondition(String condition) {

        List<User> users = new ArrayList<>();

        try (PreparedStatement statement = connection.prepareStatement(SQL_SELECT_ALL_USERS + " " + condition)) {

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                users.add(userMapper.apply(resultSet));
            }

        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }

        return users;
    }

    public void deleteById(Long id){
        try (PreparedStatement statement = connection.prepareStatement(SQL_DELETE_USER_BY_ID)) {
            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public List<User> findAll(){
        List<User> users = new ArrayList<>();

        try (PreparedStatement statement = connection.prepareStatement(SQL_SELECT_ALL_USERS)) {

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                users.add(userMapper.apply(resultSet));
            }
        }
        catch (SQLException e) {
            throw new IllegalArgumentException();
        }
        return users;
    }
}

