package com.example.aviatales2.servlets.resourse_importers;

import com.example.aviatales2.models.City;
import com.example.aviatales2.repositories.CityRepository;
import com.example.aviatales2.repositories.impl.CityRepositoryImpl;
import com.example.aviatales2.repositories.impl.TicketRepositoryImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

@WebServlet(urlPatterns = "/json-cities")
public class JsonServlet extends HttpServlet {
    private ObjectMapper objectMapper;
    private CityRepository cityRepository;

    @Override
    public void init(ServletConfig config) throws ServletException {
        this.objectMapper = (ObjectMapper) config.getServletContext().getAttribute("objectMapper");
        cityRepository = new CityRepositoryImpl((Connection) config.getServletContext().getAttribute("connection"));
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<City> cities = cityRepository.findAll();
        String jsonResponse = objectMapper.writeValueAsString(cities);
        resp.setContentType("application/json");
        resp.getWriter().write(jsonResponse);
    }


}
