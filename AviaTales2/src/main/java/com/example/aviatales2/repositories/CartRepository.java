package com.example.aviatales2.repositories;

import com.example.aviatales2.models.Cart;

import java.util.List;

public interface CartRepository {
    void save(Cart cart);
    void update(Cart cart);
    void deleteById(Long id);
    List<Cart> findByCondition(String condition);
    List<Cart> findAll();
}
