package com.example.backend.service.impl;

import com.example.backend.enity.ProductSizeQuantity;
import com.example.backend.repository.ProductRepository;
import com.example.backend.repository.ProductSizeRepository;
import com.example.backend.repository.SizeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class ProductSizeService {
    @Autowired
    private ProductSizeRepository productSizeRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private SizeRepository sizeRepository;
    @Transactional
    public ProductSizeQuantity updateProductSizeQuantity(String productName, String sizeName, long quantity) {
        // Kiểm tra xem đã tồn tại bản ghi cho (product, size) hay chưa
        Optional<ProductSizeQuantity> productSizeQuantity = productSizeRepository.findByProduct_CodeAndSize_Name(productName,sizeName);

        if (productSizeQuantity.isPresent()) {
            System.out.println("Tồn tại");
            ProductSizeQuantity productSize = productSizeQuantity.get();
            // Nếu đã tồn tại, cộng thêm vào số lượng hiện tại
            productSize.setQuantity(productSize.getQuantity() + quantity);
            return productSize;

        } else {
            System.out.println("Tồn tại cứt");
            System.out.println(sizeName);

            // Nếu không tồn tại, tạo mới bản ghi mới
            ProductSizeQuantity newRecord = new ProductSizeQuantity();

            newRecord.setProduct(productRepository.findByCode(productName));
            newRecord.setSize(sizeRepository.findByName(sizeName));
            newRecord.setQuantity(quantity);
            return newRecord;
        }
    }
}
