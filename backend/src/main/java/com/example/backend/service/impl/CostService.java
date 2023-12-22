package com.example.backend.service.impl;

import com.example.backend.enity.Cost;
import com.example.backend.enity.Products;
import com.example.backend.repository.CostRepository;
import com.example.backend.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CostService{
    @Autowired
    private CostRepository costRepository;
    @Autowired
    private ProductRepository productRepository;
    public void updatestandardCost(double standardCost, String code){
        Products products = productRepository.findByCode(code);
        if(products != null){
            Cost cost = new Cost();
            cost.setStandardCost(standardCost);
            cost.setProduct(productRepository.findByCode(code));
            costRepository.save(cost);
        }

    }
}