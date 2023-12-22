package com.example.backend.service.impl;

import com.example.backend.dto.request.ShopImportDTO;
import com.example.backend.enity.ImportDetails;
import com.example.backend.enity.ShopImports;
import com.example.backend.repository.ImportDetailsRepository;
import com.example.backend.repository.ImportsRepository;
import com.example.backend.repository.StoresRepository;
import com.example.backend.repository.UserRepository;
import com.example.backend.service.IImportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ImportService implements IImportService {
    @Autowired
    private ImportsRepository importsRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ImportDetailsRepository importDetailsRepository;
    @Autowired
    private StoresRepository storesRepository;
    public void addShopImportDTO(ShopImportDTO shopImportDTO){
        ShopImports shopImports = new ShopImports();
        shopImports.setName(shopImportDTO.getName());
        shopImports.setUser(userRepository.findByUserName(shopImportDTO.getUserName()));
        ImportDetails importDetails = importDetailsRepository.findByCode(shopImportDTO.getImportDetailsCode());

        System.out.println(importDetailsRepository.findByCode(shopImportDTO.getImportDetailsCode()).toString());
        shopImports.setStores(storesRepository.findByCode(shopImportDTO.getStoreCode()));
        shopImports.setCreateAt(shopImportDTO.getCreateAt());
        importDetails.setShopImport(shopImports);
        importsRepository.save(shopImports);
        importDetailsRepository.save(importDetails);

    }
    @Override
    public List<ShopImports> findAll() {
        return importsRepository.findAll();
    }

    @Override
    public Optional<ShopImports> findById(Long id) {
        return Optional.empty();
    }
}
