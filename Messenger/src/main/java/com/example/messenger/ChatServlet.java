package com.example.messenger;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet(urlPatterns = "/chat")
public class ChatServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession(true);

        String code = session.getAttribute("code").toString();
        String message = req.getParameter("message");
        if (message != null && message.length() > 0) {
            session.setAttribute("sessionMessage", message);
        }
        req.setAttribute("messages", MessengerSessionListener.getMessages(code));

        if (req.getParameter("message") != null) {
            resp.sendRedirect("/chat");
        } else {
            req.getRequestDispatcher("/WEB-INF/jsp/chat.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getSession(true).setAttribute("code", req.getParameter("chatCode"));
        req.getSession(true).setAttribute("sessionClientName", req.getParameter("clientName"));

        req.setAttribute("messages",
                MessengerSessionListener.getMessages(req.getSession().getAttribute("code").toString()));
        req.getRequestDispatcher("/WEB-INF/jsp/chat.jsp").forward(req, resp);
    }
}
