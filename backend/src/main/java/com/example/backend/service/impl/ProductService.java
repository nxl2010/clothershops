package com.example.backend.service.impl;

import com.amazonaws.HttpMethod;
import com.example.backend.dto.request.ProductDTO;
import com.example.backend.dto.response.AllProductDTO;
import com.example.backend.dto.response.ProductDetailsDTO;
import com.example.backend.dto.response.ProductsOfCategory;
import com.example.backend.enity.Category;
import com.example.backend.enity.Products;
import com.example.backend.enity.Supplier;
import com.example.backend.exception.CategoryNotFoundException;
import com.example.backend.exception.ProductNotFoundException;
import com.example.backend.exception.SupplierNotFoundException;
import com.example.backend.mapstructs.ProductMapper;
import com.example.backend.repository.CategoryRepository;
import com.example.backend.repository.ProductRepository;
import com.example.backend.repository.SupplierRepository;
import com.example.backend.service.FileService;
import com.example.backend.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class ProductService implements IProductService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private SupplierRepository supplierRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private RandomStringService randomStringService;

    @Autowired
    private ProductSizeService productSizeService;
    @Autowired
    private FileService fileService;

    @Override
    public List<AllProductDTO> findAll() {
        List<AllProductDTO> allProductDTOS = new ArrayList<>();
        List<Products> products = productRepository.findAll();

        if (products.size() == 0) {
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
        Products products = new Products();
        products.setCode(randomStringService.generateRandomString(5));
        products.setName(productDTO.getName());
        products.setImageUrl(productDTO.getImageUrl());
        products.setAfterImage(productDTO.getAfterImage());
        products.setShortDescription(products.getShortDescription());
        products.setNew(true);
        List<Long> prices = new ArrayList<>();
        prices.add(productDTO.getPrice());
        products.setFeatured(true);
        products.setPrices(prices);
        Optional<Supplier> supplier = supplierRepository.findByCode(productDTO.getSupplierCode());
        if (supplier.isPresent()) {
            products.setSupplier(supplier.get());
        } else {
            throw new SupplierNotFoundException(productDTO.getSupplierCode());
        }
        Optional<Category> category = categoryRepository.findByCode(productDTO.getCategoryCode());
        if (category.isPresent()) {
            products.setCategory(category.get());
        } else {
           throw new CategoryNotFoundException(productDTO.getCategoryCode());
        }
        productRepository.save(products);

    }

    @Override
    public List<ProductDTO> findBySupplierId(Long supplierId) {
        List<Products> products = productRepository.findBySupplierId(supplierId);
        return ProductMapper.INSTANCE.productsToProductDTOs(products);
    }
    //Lay danh sach san pham co cung danh muc
    @Override
    public List<ProductsOfCategory> findOfCategory(String code) {
        List<Products> allProductOfCa = productRepository.findByCategoryCode(code);
        return converToPOC(allProductOfCa);
    }
    @Override
    public Page<ProductsOfCategory> findOfCategory(String code, int page, int size) {

        PageRequest pageRequest = PageRequest.of(page, size);
        Page<Products> productsPage = productRepository.findByCategoryCode(code, pageRequest);

        List<ProductsOfCategory> productsOfCategoryList = converToPOC(productsPage.getContent());
        return new PageImpl<>(productsOfCategoryList, pageRequest, productsPage.getTotalElements());
    }


//    Lấy product theo màu và loại danh mục
    public List<ProductsOfCategory> findOfCategoryColor(String categoryCode){
        long size = (productRepository.countByCategoryCode(categoryCode) - 3)/3;
        Random random = new Random();
        int page = (int) random.nextLong(size - 0 +1);
        Pageable pageable = PageRequest.of(page, 3, Sort.by(Sort.Direction.DESC, "id"));
        Page<Products> products = productRepository.findByCategoryCode(categoryCode, pageable);
        return converToPOC(products.getContent());
    }
//    Lấy ra details
    public ProductDetailsDTO findDetailsProduct(String code){
        ProductDetailsDTO productDetailsDTO = new ProductDetailsDTO();
        Products products = productRepository.findByCode(code);
        if (products == null){
            throw new ProductNotFoundException(code);
        }
        productDetailsDTO.setCode(products.getCode());
        productDetailsDTO.setName(products.getName());
        String name =  fileService.generatePreSignedUrl(products.getImageUrl(), HttpMethod.GET);
        productDetailsDTO.setUrl(name);
        productDetailsDTO.setTotalQuantity(productSizeService.getTotalQuantityForProduct(code));
        productDetailsDTO.setSizes(productSizeService.getSizeForProduct(code));
        productDetailsDTO.setPrice(products.getPrices().get(products.getPrices().size()-1));

        return productDetailsDTO;
    }

//    Convert Product sang producofcate
    public List<ProductsOfCategory> converToPOC(List<Products> products){
        List<ProductsOfCategory> productsOfCategoryList = new ArrayList<>();
        for (Products std : products) {
            ProductsOfCategory productsOfCategory = new ProductsOfCategory();
            productsOfCategory.setName(std.getName());
            String name =  fileService.generatePreSignedUrl(std.getImageUrl(), HttpMethod.GET);
            productsOfCategory.setCode(std.getCode());
            productsOfCategory.setUrl(name);
            productsOfCategory.setPrice(std.getPrices().get(std.getPrices().size()-1));
            productsOfCategoryList.add(productsOfCategory);
        }
        return productsOfCategoryList;
    }
}
