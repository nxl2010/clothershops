package com.example.backend.service;

import com.example.backend.enity.ShopImports;

import java.util.List;
import java.util.Optional;

public interface IImportService {
    List<ShopImports> findAll();
    Optional<ShopImports> findById(Long id);
}
