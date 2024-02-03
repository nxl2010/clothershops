package com.example.backend.repository;

import com.example.backend.enity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
    List<Cart> findByCustomerCode(String code);
    List<Cart> findByCustomerId(Long id);
    Optional<Cart> findById(Long id);
}
