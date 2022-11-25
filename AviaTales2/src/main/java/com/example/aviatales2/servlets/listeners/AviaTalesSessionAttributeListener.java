package com.example.aviatales2.servlets.listeners;

import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpSessionAttributeListener;
import jakarta.servlet.http.HttpSessionBindingEvent;


public class AviaTalesSessionAttributeListener implements HttpSessionAttributeListener {
    private String username;
    private String email;

    @Override
    public void attributeAdded(HttpSessionBindingEvent event) {
        if(event.getName().equals("sessionUsername")) {
            this.username = event.getSession().getAttribute("sessionUsername").toString();
        }
        if(event.getName().equals("sessionEmail")) {
            this.email = event.getSession().getAttribute("sessionEmail").toString();
        }
    }

    @Override
    public void attributeReplaced(HttpSessionBindingEvent event) {
        if(event.getName().equals("sessionUsername")) {
            this.username = event.getSession().getAttribute("sessionUsername").toString();
        }
        if(event.getName().equals("sessionEmail")) {
            this.email = event.getSession().getAttribute("sessionEmail").toString();
        }
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }
}
