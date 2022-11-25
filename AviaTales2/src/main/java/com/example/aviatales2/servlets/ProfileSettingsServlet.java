package com.example.aviatales2.servlets;

import com.example.aviatales2.models.User;
import com.example.aviatales2.repositories.UserRepository;
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

@WebServlet(urlPatterns = SETTINGS_PATH)
public class ProfileSettingsServlet extends HttpServlet {
    public UserService userService;
    @Override
    public void init(ServletConfig config) throws ServletException {
        UserRepository userRepository
                = new UserRepositoryImpl((Connection) config.getServletContext().getAttribute("connection"));
        userService = new UserServiceImpl(userRepository);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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
            user.setEmail(req.getParameter("phone"));
            userService.updateUser(user);
        }
        if(req.getParameter("delete") != null) {
            assert user != null;
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
