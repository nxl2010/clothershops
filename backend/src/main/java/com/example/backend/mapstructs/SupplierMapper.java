package com.example.backend.mapstructs;

import com.example.backend.dto.request.SupplierDTO;
import com.example.backend.enity.Supplier;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SupplierMapper {
    SupplierMapper INSTANCE = Mappers.getMapper(SupplierMapper.class);
    @Mapping(target = "code", ignore = true)
    Supplier supplierToSupplierDTO(SupplierDTO supplierDTO);
    SupplierDTO supplierDTOTOSupplier(Supplier supplier);
    List<SupplierDTO> supplierDTOsToSuppliers(List<Supplier> suppliers);
}
