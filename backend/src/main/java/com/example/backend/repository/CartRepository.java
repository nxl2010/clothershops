package com.example.backend.repository;

import com.example.backend.enity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
    List<Cart> findByCustomer_Id(String customerId);
    List<Cart> findByCustomer_Name(String customerName);
    Optional<Cart> findById(Long id);
}
