package com.example.backend.service.impl;

import com.example.backend.enity.Stores;
import com.example.backend.repository.StoresRepository;
import com.example.backend.service.IStoresService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StoresService implements IStoresService {
    private final StoresRepository storesRepository;

    public StoresService(StoresRepository storesRepository) {
        this.storesRepository = storesRepository;
    }

    @Override
    public List<Stores> findAll() {
        return storesRepository.findAll();
    }
}
