package com.example.aviatales2.services.impl;

import com.example.aviatales2.dto.SignUpDto;
import com.example.aviatales2.models.User;
import com.example.aviatales2.repositories.UserRepository;
import com.example.aviatales2.services.UserService;

import java.util.List;
import java.util.Optional;

public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;


    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    @Override
    public void signUp(SignUpDto signUpDto) {

        User user = User.builder().name(signUpDto.getName())
                .email(signUpDto.getEmail())
                .password(signUpDto.getPassword())
                .isAdmin(false)
                .build();
        userRepository.save(user);
    }

    @Override
    public Optional<User> getUserByEmail(String email) {
        String condition = " where email = '" + email + "'";
        List<User> users = userRepository.findByCondition(condition);
        User user;
        if(users.isEmpty()) {
            user = null;
        }
        else {
            user = users.get(0);
        }
        return Optional.ofNullable(user);
    }

    @Override
    public Optional<User> getUserById(Long id) {
        String condition = " where id = " + id;
        List<User> user = userRepository.findByCondition(condition);
        return Optional.ofNullable(user.get(0));
    }

    @Override
    public void updateUser(User user) {
        userRepository.update(user);
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public void changeRights(Long id) {
        User user = getUserById(id).orElse(null);
        assert user != null;
        user.setIsAdmin(!user.getIsAdmin());
        userRepository.update(user);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}

