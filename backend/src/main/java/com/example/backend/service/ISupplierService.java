package com.example.backend.service;

import com.example.backend.dto.request.SupplierDTO;
import com.example.backend.dto.response.SupplierRDTO;

import java.util.List;

public interface ISupplierService {
    List<SupplierRDTO> getAll();
    void save(SupplierDTO supplier);
    void update(SupplierDTO supplier, Long id);
}
