package com.example.aviatales2.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

import static com.example.aviatales2.constants.Paths.*;

@WebServlet(name="bookingServlet", urlPatterns = BOOKING_PATH)
public class BookingServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("ticket", req.getSession().getAttribute("bookingTicket"));
        req.getRequestDispatcher(BOOKING_PAGE).forward(req, resp);
    }
}
