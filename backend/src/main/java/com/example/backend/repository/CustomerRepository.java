package com.example.backend.repository;

import com.example.backend.enity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Customer findByCode(String code);
    Customer findByUser_UserName(String userName);
}
