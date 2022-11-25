package com.example.aviatales2.servlets;

import com.example.aviatales2.models.Ticket;
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
import java.util.List;

import static com.example.aviatales2.constants.Paths.*;

@WebServlet(urlPatterns = ADMIN_PATH)
public class AdminServlet extends HttpServlet {
    private TicketService ticketService;
    @Override
    public void init(ServletConfig config) throws ServletException {
        TicketRepository ticketRepository =
                new TicketRepositoryImpl((Connection) config.getServletContext().getAttribute("connection"));
        this.ticketService = new TicketServiceImpl(ticketRepository);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Ticket> tickets = ticketService.getAllTickets();
        req.setAttribute("tickets", tickets);
        req.getRequestDispatcher(ADMIN_PAGE).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(req.getParameterValues("ticketDelete") != null) {
            String[] ticketIds = req.getParameterValues("ticketDelete");
            for(String ticketId: ticketIds) {
                ticketService.deleteById(Long.parseLong(ticketId));
            }
        }
        resp.sendRedirect(ADMIN_PATH);
    }
}
