package com.example.aviatales2.servlets;

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

import static com.example.aviatales2.constants.Paths.*;

@WebServlet(urlPatterns = ADMIN_PATH)
public class AdminServlet extends HttpServlet {
    private TicketService ticketService;
    private UserService userService;

    private HistoryRepository historyRepository;

    @Override
    public void init(ServletConfig config) throws ServletException {
        Connection connection = (Connection) config.getServletContext().getAttribute("connection");

        TicketRepository ticketRepository = new TicketRepositoryImpl(connection);
        UserRepository userRepository = new UserRepositoryImpl(connection);

        this.ticketService = new TicketServiceImpl(ticketRepository);
        this.userService = new UserServiceImpl(userRepository);
        this.historyRepository = new HistoryRepositoryImpl(connection);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Ticket> tickets = ticketService.getAllTickets();
        List<User> users = userService.getAllUsers();

        req.setAttribute("users", users);
        req.setAttribute("tickets", tickets);
        req.getRequestDispatcher(ADMIN_PAGE).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(req.getParameterValues("ticketDelete") != null) {
            String[] ticketIds = req.getParameterValues("ticketDelete");
            for(String ticketId: ticketIds) {
                historyRepository.deleteByTicketId(Long.parseLong(ticketId));
                ticketService.deleteById(Long.parseLong(ticketId));
            }
        }
        else if(req.getParameterValues("userDelete") != null) {
            String[] usersIds = req.getParameterValues("userDelete");
            for(String userId: usersIds) {
                historyRepository.deleteByUserId(Long.parseLong(userId));
                userService.deleteUser(Long.parseLong(userId));
            }
        }
        else if(req.getParameter("userId") != null) {
            userService.changeRights(Long.parseLong(req.getParameter("userId")));
        }
        resp.sendRedirect(ADMIN_PATH);
    }
}
