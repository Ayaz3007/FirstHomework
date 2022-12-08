package com.example.aviatales2.services;

import com.example.aviatales2.dto.SignUpDto;
import com.example.aviatales2.models.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    void signUp(SignUpDto signUpDto);
    void updateUser(User user);
    Optional<User> getUserByEmail(String email);
    Optional<User> getUserById(Long id);
    void deleteUser(Long id);
    void changeRights(Long id);
    List<User> getAllUsers();
}
