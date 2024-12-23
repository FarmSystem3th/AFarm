package com.example.farmsystem.Repository;

import com.example.farmsystem.Entity.Cart;
import com.example.farmsystem.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartRepository extends JpaRepository<Cart, Long> {
    List<Cart> findByUser(User user);
}