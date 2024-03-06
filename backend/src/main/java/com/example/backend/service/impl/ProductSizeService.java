package com.example.backend.service.impl;

import com.example.backend.dto.request.OrderdetailsDTO;
import com.example.backend.enity.ProductSizeQuantity;
import com.example.backend.exception.ProductNotFoundException;
import com.example.backend.repository.ProductRepository;
import com.example.backend.repository.ProductSizeRepository;
import com.example.backend.repository.SizeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
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
// Kiểm tra còn hàng không
    public boolean isInvento(OrderdetailsDTO orderdetailsDTO){
        Optional<ProductSizeQuantity> productSizeQuantityOptional  = productSizeRepository.findByProduct_CodeAndSize_Name(orderdetailsDTO.getProductCode(),orderdetailsDTO.getSize());
        if(productSizeQuantityOptional.isPresent()){
            long quanity = productSizeQuantityOptional.get().getQuantity();
            if (quanity >= orderdetailsDTO.getQuantity()){
                return true;
            }
            else {
                return false;
            }
        }
        else {
            throw new ProductNotFoundException("Hello");
        }
    }
//    Lấy các size có trong class
    public List<String> getSizeForProduct(String code){
        List<ProductSizeQuantity> productSizeQuantities = productSizeRepository.findByProductCode(code);
        List<String> sizes = new ArrayList<>();
        if (!productSizeQuantities.isEmpty()) {
            for (ProductSizeQuantity productSizeQuantity : productSizeQuantities) {
                sizes.add(productSizeQuantity.getSize().getName());
            }
            return sizes;
        } else {
            return new ArrayList<>();
        }
    }
//    Lấy số tổng hàng hóa theo sản phẩm
    public long getTotalQuantityForProduct(String productCode) {
        List<ProductSizeQuantity> productSizeQuantities = productSizeRepository.findByProductCode(productCode);

        if (!productSizeQuantities.isEmpty()) {
            long totalQuantity = 0;
            for (ProductSizeQuantity productSizeQuantity : productSizeQuantities) {
                totalQuantity += productSizeQuantity.getQuantity();
            }
            return totalQuantity;
        } else {
            return 0;
        }
    }
    //Trừ hàng hóa
    @Transactional
    public void reduceInveto(OrderdetailsDTO orderdetailsDTO){
        Optional<ProductSizeQuantity> productSizeQuantityOptional  = productSizeRepository.findByProduct_CodeAndSize_Name(orderdetailsDTO.getProductCode(),orderdetailsDTO.getSize());
        if(productSizeQuantityOptional.isPresent()){
            ProductSizeQuantity productSizeQuantity = productSizeQuantityOptional.get();
            long quanity = productSizeQuantity.getQuantity();
            quanity -= orderdetailsDTO.getQuantity();
            productSizeQuantity.setQuantity(quanity);
            productSizeRepository.save(productSizeQuantity);
        }
        else {
            throw new ProductNotFoundException("Hello");
        }
    }
    //Hoàn hàng vào kho
    @Transactional
    public void refundInveto(OrderdetailsDTO orderdetailsDTO){
        Optional<ProductSizeQuantity> productSizeQuantityOptional  = productSizeRepository.findByProduct_CodeAndSize_Name(orderdetailsDTO.getProductCode(),orderdetailsDTO.getSize());
        if(productSizeQuantityOptional.isPresent()){
            ProductSizeQuantity productSizeQuantity = productSizeQuantityOptional.get();
            long quanity = productSizeQuantity.getQuantity();
            quanity += orderdetailsDTO.getQuantity();
            productSizeQuantity.setQuantity(quanity);
            productSizeRepository.save(productSizeQuantity);
        }
        else {
            throw new ProductNotFoundException("Hello");
        }
    }
}
