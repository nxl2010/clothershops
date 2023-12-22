package com.example.backend.repository;

import com.example.backend.enity.Products;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Products, Long> {
    List<Products> findBySupplierId(Long supplierid);
    Products findByCode(String code);
}
