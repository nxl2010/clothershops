package com.example.backend.service.impl;

import com.example.backend.dto.request.ShopImportDTO;
import com.example.backend.dto.response.ShopImportRDTO;
import com.example.backend.enity.ImportDetails;
import com.example.backend.enity.ShopImports;
import com.example.backend.repository.ImportDetailsRepository;
import com.example.backend.repository.ImportsRepository;
import com.example.backend.repository.UserRepository;
import com.example.backend.service.IImportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    public void addShopImportDTO(ShopImportDTO shopImportDTO){
        ShopImports shopImports = new ShopImports();
        shopImports.setName(shopImportDTO.getName());
        shopImports.setUser(userRepository.findByUserName(shopImportDTO.getUserName()));
        ImportDetails importDetails = importDetailsRepository.findByCode(shopImportDTO.getImportDetailsCode());
        shopImports.setCreateAt(shopImportDTO.getCreateAt());
        importDetails.setShopImport(shopImports);
        importsRepository.save(shopImports);
        importDetailsRepository.save(importDetails);

    }

    public List<ShopImportRDTO> findAll() {
        List<ShopImports> shopImportsList = importsRepository.findAll();
        List<ShopImportRDTO> shopImports = new ArrayList<>();
        for (ShopImports std : shopImportsList){
            ShopImportRDTO shopImportRDTO = new ShopImportRDTO();
            shopImportRDTO.setName(std.getName());
            shopImportRDTO.setUserName(std.getUser().getUserName());
            shopImportRDTO.setTime(std.getCreateAt());
            shopImports.add(shopImportRDTO);
        }
        return  shopImports;
    }

    @Override
    public Optional<ShopImports> findById(Long id) {
        return Optional.empty();
    }
}
