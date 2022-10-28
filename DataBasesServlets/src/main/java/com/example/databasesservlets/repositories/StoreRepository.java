package com.example.databasesservlets.repositories;

import com.example.databasesservlets.connection.PostgresConnectionProvider;
import com.example.databasesservlets.models.Store;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class StoreRepository {
    //SQL
    private static final String SQL_SAVE_STORE = "insert into store(name) values(?);";
    //SQL
    private static final String SQL_UPDATE_STORE = "update store set name = ? where id = ?;";

    private static final String SQL_SELECT_ALL_STORES= "select * from store";

    private static final String SQL_DELETE_STORE_BY_ID = "delete from store where id = ?";
    private static final Function<ResultSet, Store> storeMapper = row -> {
        try {
            Long id = row.getLong("id");
            String name = row.getString("name");
            return new Store(id, name);
        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
    };

    public static void save(Store store){
        try(Connection connection = PostgresConnectionProvider.getConnection();
            PreparedStatement statement = connection.prepareStatement(SQL_SAVE_STORE,
                    Statement.RETURN_GENERATED_KEYS)) {

            statement.setString(1, store.getName());
            statement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean update(Store store){
        try(Connection connection = PostgresConnectionProvider.getConnection();
            PreparedStatement statement = connection.prepareStatement(SQL_UPDATE_STORE)) {

            statement.setString(1, store.getName());
            statement.setLong(2, store.getId());

            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }
    public static List<Store> findSomeEntities(String condition) {

        List<Store> stores = new ArrayList<>();

        try (Connection connection = PostgresConnectionProvider.getConnection();
             Statement statement = connection.createStatement()) {

            ResultSet resultSet = statement.executeQuery(SQL_SELECT_ALL_STORES + " " + condition);
            while (resultSet.next()) {
                stores.add(storeMapper.apply(resultSet));
            }

        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }

        return stores;
    }

    public static void deleteById(Long id){
        try (Connection connection = PostgresConnectionProvider.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_DELETE_STORE_BY_ID)) {
            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
    }
}
