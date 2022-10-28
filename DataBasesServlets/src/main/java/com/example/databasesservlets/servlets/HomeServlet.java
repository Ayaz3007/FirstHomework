package com.example.databasesservlets.servlets;

import com.example.databasesservlets.models.Product;
import com.example.databasesservlets.models.Relation;
import com.example.databasesservlets.models.Store;
import com.example.databasesservlets.repositories.ProductRepository;
import com.example.databasesservlets.repositories.RelationRepository;
import com.example.databasesservlets.repositories.StoreRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Objects;

@WebServlet(urlPatterns = "/")
public class HomeServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String inputType = req.getParameter("inputType");
        if(Objects.equals(inputType, "relation")) {
            try {
                Long productName = Long.parseLong(req.getParameter("relationProductId"));
                Long storeName = Long.parseLong(req.getParameter("relationStoreId"));
                int amount = Integer.parseInt(req.getParameter("relationAmount"));
                Relation relation;

                if (req.getParameter("requestType").equals("update")) {
                    Long id = Long.parseLong(req.getParameter("id"));
                    relation = new Relation(id, productName, storeName, amount);
                    if(!RelationRepository.update(relation)){
                        req.setAttribute("error", true);
                        req.getRequestDispatcher("/WEB-INF/jsp/home.jsp").forward(req, resp);
                    };
                } else {
                    relation = new Relation(productName, storeName, amount);
                    if(!RelationRepository.save(relation)){
                        req.setAttribute("error", true);
                        req.getRequestDispatcher("/WEB-INF/jsp/home.jsp").forward(req, resp);
                    };
                }
            }
            catch (NumberFormatException e){
                req.setAttribute("error", true);
                req.getRequestDispatcher("/WEB-INF/jsp/home.jsp").forward(req, resp);
            }
        }
        else{
            String name = req.getParameter("entity");
            try {
                if (inputType.equals("product")) {
                    Product product;
                    if (req.getParameter("requestType").equals("update")) {
                        Long id = Long.parseLong(req.getParameter("id"));
                        product = new Product(id, name);
                        if(!ProductRepository.update(product)){
                            req.setAttribute("error", true);
                            req.getRequestDispatcher("/WEB-INF/jsp/home.jsp").forward(req, resp);
                        };
                    } else {
                        product = new Product(name);
                        ProductRepository.save(product);
                    }
                } else {
                    Store store;
                    if (req.getParameter("requestType").equals("update")) {
                        Long id = Long.parseLong(req.getParameter("id"));
                        store = new Store(id, name);
                        if(!StoreRepository.update(store)){
                            req.setAttribute("error", true);
                            req.getRequestDispatcher("/WEB-INF/jsp/home.jsp").forward(req, resp);
                        };
                    } else {
                        store = new Store(name);
                        StoreRepository.save(store);
                    }
                }
            }
            catch (NumberFormatException e){
                req.setAttribute("error", true);
                req.getRequestDispatcher("/WEB-INF/jsp/home.jsp").forward(req, resp);
            }
        }
        req.setAttribute("error", false);
        resp.sendRedirect("/");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("error", false);
        req.getRequestDispatcher("/WEB-INF/jsp/home.jsp").forward(req, resp);
    }
}
