package com.example.aviatales2.repositories;

import com.example.aviatales2.models.User;

import java.util.List;

public interface UserRepository {
    void save(User user);
    void update(User user);
    void deleteById(Long id);
    List<User> findByCondition(String condition);
    List<User> findAll();
}
