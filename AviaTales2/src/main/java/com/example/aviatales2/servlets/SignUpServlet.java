package com.example.aviatales2.servlets;

import com.example.aviatales2.dto.SignUpDto;
import com.example.aviatales2.dto.converters.HttpFormsConverter;
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

@WebServlet(name = "signUpServlet", urlPatterns = SIGN_UP_PATH)
public class SignUpServlet extends HttpServlet {
    private UserService userService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        UserRepository userRepository
                = new UserRepositoryImpl((Connection) config.getServletContext().getAttribute("connection"));
        userService = new UserServiceImpl(userRepository);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(SIGN_UP_PAGE).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        SignUpDto signUpData = HttpFormsConverter.from(req);
        userService.signUp(signUpData);
        resp.sendRedirect(START_PATH);
    }
}
