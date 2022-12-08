package com.example.aviatales2.servlets;


import com.example.aviatales2.models.City;
import com.example.aviatales2.repositories.CityRepository;
import com.example.aviatales2.repositories.impl.CityRepositoryImpl;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;
import java.util.stream.Collectors;


import static com.example.aviatales2.constants.Paths.*;

@WebServlet(name="mainPage", urlPatterns = START_PATH)
public class StartPageServlet extends HttpServlet {
    private CityRepository cityRepository;
    @Override
    public void init(ServletConfig config) throws ServletException {
        cityRepository = new CityRepositoryImpl((Connection) config.getServletContext().getAttribute("connection"));
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<String> cities = cityRepository.findAll().stream().map(City::getName).collect(Collectors.toList());
        req.setAttribute("cities", cities);
        req.getRequestDispatcher(START_PAGE).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
