package com.example.backend.service.impl;

import com.example.backend.enity.Price;
import com.example.backend.enity.Products;
import com.example.backend.repository.PriceRepository;
import com.example.backend.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PriceService {
    @Autowired
    private PriceRepository priceRepository;
    @Autowired
    private ProductRepository productRepository;
    public void updatePriceCost(double standardCost, String code){
        Products products = productRepository.findByCode(code);
        if(products != null){
            Price price = new Price();
            price.setProduct(products);
            priceRepository.save(price);
        }

    }
}
