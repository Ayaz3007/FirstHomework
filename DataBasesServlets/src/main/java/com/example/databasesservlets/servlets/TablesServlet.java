package com.example.databasesservlets.servlets;

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

@WebServlet(urlPatterns = "/tables")
public class TablesServlet extends HttpServlet {
    private static String productCondition = "";
    private static String storeCondition = "";
    private static String relationCondition = "";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(Objects.equals(req.getParameter("searchType"), "back")){
            productCondition = "";
            storeCondition = "";
            relationCondition = "";
        }
        else if(Objects.equals(req.getParameter("searchType"), "product")){
            productCondition = "where name like '%" + req.getParameter("searchProduct") + "%'";
        }
        else if(Objects.equals(req.getParameter("searchType"), "store")){
            storeCondition = "where name like '%" + req.getParameter("searchStore") + "%'";
        }
        else if(Objects.equals(req.getParameter("searchType"), "relation")){
            relationCondition = "where ";
            String productId = req.getParameter("searchRelationProductId");
            String storeId = req.getParameter("searchRelationStoreId");
            if(!productId.equals("")){
                relationCondition += "product_id = '" + req.getParameter("searchRelationProductId") + "'";
            }
            if(!storeId.equals("")){
                if(!productId.equals("")){
                    relationCondition += " and ";
                }
                relationCondition += "store_id = '" + req.getParameter("searchRelationStoreId") + "'";
            }
        }
        req.setAttribute("listOfProducts", ProductRepository.findEntities(productCondition));
        req.setAttribute("listOfStores", StoreRepository.findSomeEntities(storeCondition));
        req.setAttribute("listOfRelations", RelationRepository.findSomeEntities(relationCondition));
        req.getRequestDispatcher("/WEB-INF/jsp/tables.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(req.getParameter("deleteType").equals("product")) {
            for(String product: req.getParameterValues("productDelete")) {
                Long productId = Long.parseLong(product);
                RelationRepository.deleteByProductId(productId);
                ProductRepository.deleteById(productId);
            }
        } else if (req.getParameter("deleteType").equals("store")) {
            for(String store: req.getParameterValues("storeDelete")) {
                Long storeId = Long.parseLong(store);
                RelationRepository.deleteByStoreId(storeId);
                StoreRepository.deleteById(storeId);
            }
        }
        else {
            for (String relation : req.getParameterValues("relationDelete")){
                RelationRepository.deleteById(Long.parseLong(relation));
            }
        }
        resp.sendRedirect("/tables");
    }
}
