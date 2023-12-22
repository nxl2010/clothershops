package com.example.backend.service;

import com.example.backend.dto.request.SupplierDTO;

import java.util.List;

public interface ISupplierService {
    List<SupplierDTO> getAll();
    void save(SupplierDTO supplier);
    void update(SupplierDTO supplier, Long id);
}
