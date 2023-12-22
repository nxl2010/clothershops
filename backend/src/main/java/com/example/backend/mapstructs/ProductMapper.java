package com.example.backend.mapstructs;

import com.example.backend.dto.request.ProductDTO;
import com.example.backend.enity.Products;
import com.example.backend.enity.Supplier;
import com.example.backend.exception.SupplierNotFoundException;
import com.example.backend.repository.SupplierRepository;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.Optional;

@Mapper(componentModel = "spring", uses = {SupplierMapper.class})
public interface ProductMapper {
    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    @Mapping(source = "supplier.code", target = "supplierCode")
    ProductDTO productToProductDTO(Products products);

    List<ProductDTO> productsToProductDTOs(List<Products> productsList);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "code", ignore = true)
    Products productDTOToProduct(ProductDTO productDTO, @Context SupplierRepository supplierRepository);

    @AfterMapping
    default void populateSupplierFromDTO(ProductDTO productDTO, @MappingTarget Products products, @Context SupplierRepository supplierRepository) {
        Optional<Supplier> supplier = supplierRepository.findByCode(productDTO.getSupplierCode());
        if(!supplier.isPresent()){
            throw new SupplierNotFoundException(productDTO.getName());
        }
            products.setSupplier(supplier.get());

    }
}

