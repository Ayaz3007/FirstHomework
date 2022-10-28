package com.example.databasesservlets.repositories;

import com.example.databasesservlets.connection.PostgresConnectionProvider;
import com.example.databasesservlets.models.Relation;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class RelationRepository {

    private static final String SQL_SAVE_RELATION = "insert into product_to_store(product_id, store_id, amount) " +
            "values(?, ?, ?);";
    //SQL
    private static final String SQL_UPDATE_RELATION = "update product_to_store set product_id = ?," +
            "store_id = ?," +
            "amount = ?" +
            " where id = ?;";

    private static final String SQL_SELECT_ALL_RELATIONS= "select * from product_to_store";

    private static final String SQL_DELETE_RELATION_BY_ID = "delete from product_to_store where id = ?";
    private static final String SQL_DELETE_RELATION_BY_PRODUCT_ID = "delete from product_to_store where product_id = ?";

    private static final String SQL_DELETE_RELATION_BY_STORE_ID = "delete from product_to_store where store_id = ?";

    private static final Function<ResultSet, Relation> relationMapper = row -> {
        try {

            Long id = row.getLong("id");
            Long product_id = row.getLong("product_id");
            Long store_id = row.getLong("store_id");
            int amount = row.getInt("amount");

            return new Relation(id, product_id, store_id, amount);
        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
    };

    public static boolean save(Relation relation){
        try(Connection connection = PostgresConnectionProvider.getConnection();
            PreparedStatement statement = connection.prepareStatement(SQL_SAVE_RELATION,
                    Statement.RETURN_GENERATED_KEYS)) {

            statement.setLong(1, relation.getProductId());
            statement.setLong(2, relation.getStoreId());
            statement.setInt(3, relation.getAmount());
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    public static boolean update(Relation relation){
        try(Connection connection = PostgresConnectionProvider.getConnection();
            PreparedStatement statement = connection.prepareStatement(SQL_UPDATE_RELATION)) {

            statement.setLong(1, relation.getProductId());
            statement.setLong(2, relation.getStoreId());
            statement.setInt(3, relation.getAmount());
            statement.setLong(4, relation.getId());
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    public static List<Relation> findSomeEntities(String condition) {

        List<Relation> relations = new ArrayList<>();

        try (Connection connection = PostgresConnectionProvider.getConnection();
             Statement statement = connection.createStatement()) {

            ResultSet resultSet = statement.executeQuery(SQL_SELECT_ALL_RELATIONS + " " + condition);
            while (resultSet.next()) {
                relations.add(relationMapper.apply(resultSet));
            }

        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }

        return relations;
    }

    public static void deleteById(Long id){
        try (Connection connection = PostgresConnectionProvider.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_DELETE_RELATION_BY_ID)) {
            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public static void deleteByProductId(Long productId){
        try (Connection connection = PostgresConnectionProvider.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_DELETE_RELATION_BY_PRODUCT_ID)) {
            statement.setLong(1, productId);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
    }
    public static void deleteByStoreId(Long storeId){
        try (Connection connection = PostgresConnectionProvider.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_DELETE_RELATION_BY_STORE_ID)) {
            statement.setLong(1, storeId);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
    }
}

