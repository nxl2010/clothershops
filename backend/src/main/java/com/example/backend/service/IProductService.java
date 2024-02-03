package com.example.backend.service;

import com.example.backend.dto.request.ProductDTO;
import com.example.backend.dto.response.ProductsOfCategory;
import org.springframework.data.domain.Page;

import java.util.List;

public interface IProductService {
     <T> List<T> findAll();

    void save(ProductDTO productDTO);

    List<ProductDTO> findBySupplierId(Long supplierId);

    List<ProductsOfCategory> findOfCategory(String code);

    Page<ProductsOfCategory> findOfCategory(String code, int page, int size);
}
