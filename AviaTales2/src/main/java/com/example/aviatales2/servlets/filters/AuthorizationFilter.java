package com.example.aviatales2.servlets.filters;

import com.example.aviatales2.connection.PostgresConnectionProvider;
import com.example.aviatales2.servlets.filters.managers.AuthenticationManager;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import static com.example.aviatales2.constants.Paths.*;

@WebFilter(urlPatterns = "/sign-in")
public class AuthorizationFilter extends HttpFilter {
    private AuthenticationManager authenticationManager;
    @Override
    public void init() throws ServletException {
        this.authenticationManager = new AuthenticationManager(PostgresConnectionProvider.getConnection());
    }

    @Override
    protected void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {

        if (request.getMethod().equals("POST")) {
            String email = request.getParameter("email");
            String password = request.getParameter("password");

            if (authenticationManager.authenticate(email, password)) {
                HttpSession session = request.getSession(true);
                session.setAttribute("authenticated", true);
                session.setAttribute("email", email);
                session.setAttribute("isAdmin", authenticationManager.isAdmin(email));
                response.sendRedirect(START_PATH);
            } else {
                response.sendRedirect(SIGN_IN_PATH + "?error=true");
            }
        } else {
            chain.doFilter(request, response);
        }
    }
}
