package com.example.backend.service.impl;

import com.example.backend.dto.request.ProductDTO;
import com.example.backend.dto.response.AllProductDTO;
import com.example.backend.enity.Products;
import com.example.backend.exception.ProductNotFoundException;
import com.example.backend.mapstructs.ProductMapper;
import com.example.backend.repository.ProductRepository;
import com.example.backend.repository.SupplierRepository;
import com.example.backend.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService implements IProductService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private SupplierRepository supplierRepository;
    @Autowired
    private RandomStringService randomStringService;
    @Override
    public List<AllProductDTO> findAll() {
        List<AllProductDTO> allProductDTOS = new ArrayList<>();
        List<Products> products = productRepository.findAll();

        if (products.size() == 0){
            throw new ProductNotFoundException("Null");
        }
        for (Products std : products) {
            AllProductDTO allProductDTO = new AllProductDTO();
            allProductDTO.setCode(std.getCode());
            allProductDTO.setName(std.getName());
            allProductDTO.setSupplier(std.getSupplier() != null ? std.getSupplier().getName() : "Chưa có");
            allProductDTO.setCategory(std.getCategory() != null ? std.getCategory().getName() : "Chưa có");
//            double totalQuantity = std.getSizeQuantities().stream()
//                    .mapToLong(ProductSizeQuantity::getQuantity)
//                    .sum();
//            allProductDTO.setQuantity(totalQuantity);
//
//            if (!std.getPrices().isEmpty()) {
//                double latestListPrice = std.getPrices().get(std.getPrices().size() - 1).getListPrice();
//                allProductDTO.setPrice(latestListPrice);
//            } else {
//                // Nếu danh sách prices rỗng, set giá trị mặc định
//                allProductDTO.setPrice(0.0);
//            }

            allProductDTOS.add(allProductDTO);
        }
        return allProductDTOS;

    }

    @Override
    public void save(ProductDTO productDTO) {
        Products products = ProductMapper.INSTANCE.productDTOToProduct(productDTO, supplierRepository);
        products.setCode(randomStringService.generateRandomString(5));
        productRepository.save(products);
    }

    @Override
    public List<ProductDTO> findBySupplierId(Long supplierId) {
        List<Products> products = productRepository.findBySupplierId(supplierId);
        return ProductMapper.INSTANCE.productsToProductDTOs(products);
    }
}
