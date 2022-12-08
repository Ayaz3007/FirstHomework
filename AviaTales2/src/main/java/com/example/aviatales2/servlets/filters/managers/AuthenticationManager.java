package com.example.aviatales2.servlets.filters.managers;

import com.example.aviatales2.models.User;
import com.example.aviatales2.repositories.impl.UserRepositoryImpl;
import com.example.aviatales2.services.UserService;
import com.example.aviatales2.services.impl.UserServiceImpl;

import java.sql.Connection;

public class AuthenticationManager {
    private final UserService userService;

    public AuthenticationManager(Connection connection) {
        this.userService = new UserServiceImpl(new UserRepositoryImpl(connection));
    }

    public boolean authenticate(String email, String password) {
        User user = userService.getUserByEmail(email).orElse(null);
        if(user == null) {
            return false;
        }
        return user.getPassword().equals(password);
    }

    public boolean isAdmin(String email) {
        User user = userService.getUserByEmail(email).orElse(null);
        if(user == null) {
            return false;
        }
        return user.getIsAdmin();
    }
}
