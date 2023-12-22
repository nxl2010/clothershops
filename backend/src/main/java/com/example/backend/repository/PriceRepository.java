package com.example.backend.repository;

import com.example.backend.enity.Price;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PriceRepository extends JpaRepository<Price, Long> {
    Price findByProduct_Code(String productCode);
}
