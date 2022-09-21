package org.example;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@ContentServlet(contentName = "Billy Talent - Rusted from the Rain")
@WebServlet("/rusted-from-the-rain")
public class MusicServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException
    {
        request.getRequestDispatcher("/RustedFromTheRain.html").forward(request, response);
    }
}
