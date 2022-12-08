package com.example.aviatales2.servlets;

import com.example.aviatales2.dto.TicketDto;
import com.example.aviatales2.models.FlightHistory;
import com.example.aviatales2.models.Ticket;
import com.example.aviatales2.models.User;
import com.example.aviatales2.repositories.HistoryRepository;
import com.example.aviatales2.repositories.TicketRepository;
import com.example.aviatales2.repositories.UserRepository;
import com.example.aviatales2.repositories.impl.HistoryRepositoryImpl;
import com.example.aviatales2.repositories.impl.TicketRepositoryImpl;
import com.example.aviatales2.repositories.impl.UserRepositoryImpl;
import com.example.aviatales2.services.TicketService;
import com.example.aviatales2.services.UserService;
import com.example.aviatales2.services.impl.TicketServiceImpl;
import com.example.aviatales2.services.impl.UserServiceImpl;
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

@WebServlet(urlPatterns = SETTINGS_PATH)
public class ProfileSettingsServlet extends HttpServlet {
    public UserService userService;
    public HistoryRepository historyRepository;

    public TicketService ticketService;
    @Override
    public void init(ServletConfig config) throws ServletException {
        Connection connection = (Connection) config.getServletContext().getAttribute("connection");

        UserRepository userRepository = new UserRepositoryImpl(connection);
        TicketRepository ticketRepository = new TicketRepositoryImpl(connection);

        this.userService = new UserServiceImpl(userRepository);
        this.historyRepository = new HistoryRepositoryImpl(connection);
        this.ticketService = new TicketServiceImpl(ticketRepository);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = userService.getUserByEmail(req.getSession().getAttribute("email").toString()).orElse(null);
        assert user != null;
        List<FlightHistory> flightHistory = historyRepository.findByUserId(user.getId());
        List<TicketDto> tickets = flightHistory.stream().map(x -> ticketService.getTicketById(x.getTicketId())).collect(Collectors.toList());
        req.setAttribute("tickets", tickets);
        req.getRequestDispatcher(SETTINGS_PAGE).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = userService.getUserByEmail(req.getSession().getAttribute("email").toString()).orElse(null);
        if(req.getParameter("email") != null) {
            assert user != null;
            user.setEmail(req.getParameter("email"));
            req.getSession().setAttribute("email", user.getEmail());
            userService.updateUser(user);
        }
        if(req.getParameter("phone") != null) {
            assert user != null;
            user.setPhone(req.getParameter("phone"));
            userService.updateUser(user);
        }
        if(req.getParameter("delete") != null) {
            assert user != null;
            historyRepository.deleteByUserId(user.getId());
            userService.deleteUser(user.getId());
            req.getSession().setAttribute("authenticated", false);
            resp.sendRedirect(START_PATH);
            return;
        }
        if(req.getParameter("exit") != null) {
            req.getSession().setAttribute("authenticated", false);
            resp.sendRedirect(START_PATH);
            return;
        }
        resp.sendRedirect(SETTINGS_PATH);
    }
}
