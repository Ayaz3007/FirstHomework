package com.example.aviatales2.repositories.impl;

import com.example.aviatales2.models.Cart;
import com.example.aviatales2.repositories.CartRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class CartRepositoryImpl implements CartRepository {
    private final Connection connection;

    private static final String SQL_SAVE_CART = "insert into ticket_cart(username_id, ticket_id) values(?, ?);";
    //SQL
    private static final String SQL_UPDATE_CART = "update ticket_cart set username_id = ?, ticket_id = ?" +
            " where id = ?;";
    private static final String SQL_SELECT_ALL_CARTS = "select * from ticket_cart";

    private static final String SQL_DELETE_CART_BY_ID = "delete from ticket_cart where id = ?";

    public CartRepositoryImpl(Connection connection) {
        this.connection = connection;
    }


    private static final Function<ResultSet, Cart> cartMapper = row -> {
        try {
            Long id = row.getLong("id");
            Long usernameId = row.getLong("username_id");
            Long ticket_id = row.getLong("ticket_id");

            return new Cart(id, usernameId, ticket_id);
        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
    };

    public void save(Cart cart){
        try (PreparedStatement statement = connection.prepareStatement(SQL_SAVE_CART)) {

            statement.setLong(1, cart.getUsernameId());
            statement.setLong(2, cart.getTicketId());
            statement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void update(Cart cart){
        try(PreparedStatement statement = connection.prepareStatement(SQL_UPDATE_CART)) {

            statement.setLong(1, cart.getUsernameId());
            statement.setLong(2, cart.getTicketId());
            statement.setLong(3, cart.getId());

            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }

    public List<Cart> findByCondition(String condition) {

        List<Cart> carts = new ArrayList<>();

        try (PreparedStatement statement = connection.prepareStatement(SQL_SELECT_ALL_CARTS + " " + condition)) {

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                carts.add(cartMapper.apply(resultSet));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return carts;
    }

    public void deleteById(Long id){
        try (PreparedStatement statement = connection.prepareStatement(SQL_DELETE_CART_BY_ID)) {
            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public List<Cart> findAll(){
        List<Cart> carts = new ArrayList<>();

        try (PreparedStatement statement = connection.prepareStatement(SQL_SELECT_ALL_CARTS)) {

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                carts.add(cartMapper.apply(resultSet));
            }
        }
        catch (SQLException e) {
            throw new IllegalArgumentException();
        }
        return carts;
    }
}
