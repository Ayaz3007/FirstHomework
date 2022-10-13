package com.example.orders;

import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpSessionAttributeListener;
import jakarta.servlet.http.HttpSessionBindingEvent;

import java.util.ArrayList;
import java.util.List;

@WebListener
public class OrderSessionListener implements HttpSessionAttributeListener {
    private static final List<List<String>> orders = new ArrayList<>();

    @Override
    public void attributeAdded(HttpSessionBindingEvent event) {
        if(event.getName().equals("order")){
            orders.add((List<String>) event.getValue());
        }
    }

    @Override
    public void attributeReplaced(HttpSessionBindingEvent event) {
        if(event.getName().equals("order")){
            orders.add((List<String>) event.getSession().getAttribute("order"));
        }
    }

    public static List<List<String>> getOrders(){
        return List.copyOf(orders);
    }
}
