package com.example.backend.repository;

import com.example.backend.enity.ProductSizeQuantity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductSizeRepository extends JpaRepository<ProductSizeQuantity, Long> {
    Optional<ProductSizeQuantity> findByProduct_CodeAndSize_Name(String productCode, String sizeName);
}
