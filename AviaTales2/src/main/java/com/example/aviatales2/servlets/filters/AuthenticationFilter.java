package com.example.aviatales2.servlets.filters;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import static com.example.aviatales2.constants.Paths.*;

@WebFilter(urlPatterns = {"/settings", "/search/booking", "/admin"})
public class AuthenticationFilter extends HttpFilter {

    @Override
    protected void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpSession session = request.getSession(false);

        if (session != null) {
            Boolean authenticated = (Boolean) session.getAttribute("authenticated");
            if (authenticated != null && authenticated) {
                chain.doFilter(request, response);
                return;
            }
        }
        response.sendRedirect(SIGN_IN_PATH);
    }
}
