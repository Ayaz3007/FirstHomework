package com.example.messenger;

import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpSessionAttributeListener;
import jakarta.servlet.http.HttpSessionBindingEvent;

import java.util.*;

@WebListener
public class MessengerSessionListener implements HttpSessionAttributeListener {
    private static final Map<String, List<String>> chatRooms = new HashMap<>();
    private static String code;

    @Override
    public void attributeAdded(HttpSessionBindingEvent event) {
        if(Objects.equals(event.getName(), "sessionMessage")) {
            String message = event.getSession().getAttribute("sessionClientName") + ": " + event.getValue();
            chatRooms.computeIfAbsent(code, key -> new LinkedList<>()).add(message);
        }
    }

    @Override
    public void attributeReplaced(HttpSessionBindingEvent event) {
        if(Objects.equals(event.getName(), "sessionMessage")) {
            String message = event.getSession().getAttribute("sessionClientName") + ": "
                    + event.getSession().getAttribute("sessionMessage");
            chatRooms.computeIfAbsent(code, key -> new LinkedList<>()).add(message);
        }
    }

    public static List<String> getMessages(String code){
        MessengerSessionListener.code = code;
        if(chatRooms.get(code) != null){
            return chatRooms.get(code);
        }
        return Collections.emptyList();
    }
}
