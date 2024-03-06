package com.example.backend.repository;

import com.example.backend.enity.Order;
import com.example.backend.enity.OrderShip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    Optional<Order> findByCode(String code);
    List<Order> findByCustomerCode(String code);
    List<Order> findByCustomerCodeAndShipStatus(String code, OrderShip orderShip);
    List<Order> findByShipStatus(OrderShip orderShip);
}
