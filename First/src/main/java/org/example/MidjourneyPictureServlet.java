package org.example;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@ContentServlet(contentName = "Example of picture from Midjourney")
@WebServlet("/midjourney-picture")
public class MidjourneyPictureServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException
    {
        request.getRequestDispatcher("/MidjourneyExamples.html").forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException
    {
        request.getRequestDispatcher("/MidjourneyExamples.html").forward(request, response);
    }
}
