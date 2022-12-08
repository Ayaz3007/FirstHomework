package com.example.aviatales2.servlets;

import com.example.aviatales2.models.FlightHistory;
import com.example.aviatales2.models.User;
import com.example.aviatales2.repositories.HistoryRepository;
import com.example.aviatales2.repositories.UserRepository;
import com.example.aviatales2.repositories.impl.HistoryRepositoryImpl;
import com.example.aviatales2.repositories.impl.UserRepositoryImpl;
import com.example.aviatales2.services.UserService;
import com.example.aviatales2.services.impl.UserServiceImpl;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;

import static com.example.aviatales2.constants.Paths.*;

@WebServlet(name="bookingServlet", urlPatterns = BOOKING_PATH)
public class BookingServlet extends HttpServlet {
    private HistoryRepository historyRepository;
    private UserService userService;
    @Override
    public void init(ServletConfig config) throws ServletException {
        Connection connection = (Connection) config.getServletContext().getAttribute("connection");
        UserRepository userRepository = new UserRepositoryImpl(connection);

        this.userService = new UserServiceImpl(userRepository);
        this.historyRepository = new HistoryRepositoryImpl(connection);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long ticketId = Long.parseLong(req.getParameter("ticket"));
        User user = userService.getUserByEmail(req.getSession().getAttribute("email").toString()).orElse(null);
        assert user != null;
        FlightHistory flightHistory = FlightHistory.builder()
                .usernameId(user.getId())
                .ticketId(ticketId)
                .build();
        historyRepository.save(flightHistory);
        resp.sendRedirect(SETTINGS_PATH);
    }
}
