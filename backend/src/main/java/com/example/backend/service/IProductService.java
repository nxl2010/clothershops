package com.example.backend.service;

import com.example.backend.dto.request.ProductDTO;

import java.util.List;

public interface IProductService {
     <T> List<T> findAll();

    void save(ProductDTO productDTO);

    List<ProductDTO> findBySupplierId(Long supplierId);
}
