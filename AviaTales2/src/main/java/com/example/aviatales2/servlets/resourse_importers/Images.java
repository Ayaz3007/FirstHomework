package com.example.aviatales2.servlets.resourse_importers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

@WebServlet("/images/*")
public class Images extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        OutputStream outputStream = resp.getOutputStream();
        String fileName = req.getPathInfo().substring(1);
        resp.setContentType(req.getServletContext().getMimeType(fileName));
        Files.copy(Paths.get(getServletContext().getRealPath("images") + "\\" + fileName), outputStream);
    }
}
