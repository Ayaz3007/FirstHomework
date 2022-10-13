package com.example.orders;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@WebServlet(urlPatterns = "/create_order")
public class CreatingOrdersServlet extends HttpServlet {
    private final String SQL_GET_ALL_PRODUCTS = "select name from product_list";
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection connection = PostgresConnectionProvider.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement(SQL_GET_ALL_PRODUCTS);
            ResultSet resultSet = statement.executeQuery();
            List<String> products = new ArrayList<>();
            while(resultSet.next()){
                products.add(resultSet.getString("name"));
            }

            req.setAttribute("productsList", products);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        req.getRequestDispatcher("/WEB-INF/jsp/create_order.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection connection = PostgresConnectionProvider.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement(SQL_GET_ALL_PRODUCTS);
            ResultSet resultSet = statement.executeQuery();
            List<String> products = new ArrayList<>();
            while(resultSet.next()){
                products.add(resultSet.getString("name"));
            }
            req.setAttribute("productsList", products);
            List<String> order = new ArrayList<>();
            for(String product: products){
                if(!Objects.equals(req.getParameter(product), "true")){
                    order.add(product);
                }
            }
            req.getSession().setAttribute("order", order);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        req.getRequestDispatcher("/WEB-INF/jsp/create_order.jsp").forward(req, resp);
    }
}
