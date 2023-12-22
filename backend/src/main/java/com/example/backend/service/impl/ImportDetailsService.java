package com.example.backend.service.impl;

import com.example.backend.dto.request.ImportDetailsDTO;
import com.example.backend.dto.request.QuantityProductDTO;
import com.example.backend.enity.ImportDetails;
import com.example.backend.enity.ProductSizeQuantity;
import com.example.backend.repository.ImportDetailsRepository;
import com.example.backend.repository.ProductRepository;
import com.example.backend.service.IImportDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Service
public class ImportDetailsService implements IImportDetailsService {
    @Autowired
    private ImportDetailsRepository importDetailsRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ProductSizeService productSizeService;
    @Autowired
    private CostService costService;
    @Autowired
    private RandomStringService randomStringService;
    @Override
    public void save(ImportDetailsDTO importDetailsDTO) {
        ImportDetails importDetails = new ImportDetails();
        importDetails.setCode(randomStringService.generateRandomString(5));
        importDetails.setDeliver(importDetailsDTO.getDeliver());
        importDetails.setSumPrice(importDetailsDTO.getSumPrice());
        Set<ProductSizeQuantity> productSizeQuantities = new HashSet<>();
        for (QuantityProductDTO std : importDetailsDTO.getQuantityProductDTO()){

            String productCode = std.getProductCode();
            costService.updatestandardCost(std.getPrice(),productCode);


            Map<String, Long> sizeQuantities = std.getSizeQuantities();
            for (Map.Entry<String, Long> entry : sizeQuantities.entrySet()){
                ProductSizeQuantity productSizeQuantity = new ProductSizeQuantity();
                productSizeQuantity = productSizeService.updateProductSizeQuantity(productCode, entry.getKey(), entry.getValue());
                productSizeQuantities.add(productSizeQuantity);
            }
            importDetails.setProductSizeQuantities(productSizeQuantities);
            // Lưu ImportDetails vào cơ sở dữ liệu

        }
        importDetailsRepository.save(importDetails);
    }

    @Override
    public ImportDetails findAll() {
        return importDetailsRepository.findByCode("K1Sxd");
    }
}
