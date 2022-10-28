package com.example.databasesservlets.repositories;

import com.example.databasesservlets.connection.PostgresConnectionProvider;
import com.example.databasesservlets.models.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class ProductRepository {

    //SQL
    private static final String SQL_SAVE_PRODUCT = "insert into product(name) values(?);";
    //SQL
    private static final String SQL_UPDATE_PRODUCT = "update product set name = ? where id = ?;";
    private static final String SQL_SELECT_ALL_PRODUCTS = "select * from product";

    private static final String SQL_DELETE_PRODUCT_BY_ID = "delete from product where id = ?";

    private static final Function<ResultSet, Product> productMapper = row -> {
        try {
            Long id = row.getLong("id");
            String name = row.getString("name");

            return new Product(id, name);
        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
    };

    public static void save(Product product){
        try(Connection connection = PostgresConnectionProvider.getConnection();
            PreparedStatement statement = connection.prepareStatement(SQL_SAVE_PRODUCT,
                    Statement.RETURN_GENERATED_KEYS)) {

            statement.setString(1, product.getName());
            statement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean update(Product product){
        try(Connection connection = PostgresConnectionProvider.getConnection();
        PreparedStatement statement = connection.prepareStatement(SQL_UPDATE_PRODUCT)) {

            statement.setString(1, product.getName());
            statement.setLong(2, product.getId());

            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    public static List<Product> findEntities(String condition) {

        List<Product> products = new ArrayList<>();

        try (Connection connection = PostgresConnectionProvider.getConnection();
             Statement statement = connection.createStatement()) {

            ResultSet resultSet = statement.executeQuery(SQL_SELECT_ALL_PRODUCTS + " " + condition);
            while (resultSet.next()) {
                products.add(productMapper.apply(resultSet));
            }

        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }

        return products;
    }
    public static void deleteById(Long id){
        try (Connection connection = PostgresConnectionProvider.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_DELETE_PRODUCT_BY_ID)) {
            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
    }
}
