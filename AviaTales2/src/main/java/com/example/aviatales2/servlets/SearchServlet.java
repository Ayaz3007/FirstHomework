package com.example.aviatales2.servlets;

import com.example.aviatales2.dto.SearchTicketDto;
import com.example.aviatales2.repositories.TicketRepository;
import com.example.aviatales2.repositories.impl.TicketRepositoryImpl;
import com.example.aviatales2.services.TicketService;
import com.example.aviatales2.services.impl.TicketServiceImpl;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;


import static com.example.aviatales2.constants.Paths.*;

@WebServlet(name="searchServlet", urlPatterns = SEARCH_PATH)
public class SearchServlet extends HttpServlet {
    private TicketService ticketService;
    @Override
    public void init(ServletConfig config) throws ServletException {
        TicketRepository ticketRepository =
                new TicketRepositoryImpl((Connection) config.getServletContext().getAttribute("connection"));
        this.ticketService = new TicketServiceImpl(ticketRepository);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(SEARCH_PAGE).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        SearchTicketDto searchTicketData = SearchTicketDto.builder().fromCityName(req.getParameter("from"))
                        .toCityName(req.getParameter("to"))
                                .fromDate(Date.valueOf(req.getParameter("fdate")))
                                        .toDate(Date.valueOf(req.getParameter("adate"))).build();
        req.setAttribute("tickets", ticketService.searchTickets(searchTicketData));
        req.getRequestDispatcher(SEARCH_PAGE).forward(req, resp);
    }

}
