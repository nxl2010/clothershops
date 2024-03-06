package com.example.backend.repository;

import com.example.backend.enity.Products;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Products, Long> {
    List<Products> findBySupplierId(Long supplierid);
    Products findByCode(String code);
    List<Products> findByCategoryCode(String code);
    Page<Products> findByCategoryCode(String categoryCode, Pageable pageable);
    List<Products> findByCategoryCodeAndColor(String code, String color, Pageable pageable);
    long countByCategoryCode(String code);

}
