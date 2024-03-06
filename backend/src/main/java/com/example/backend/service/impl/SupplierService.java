package com.example.backend.service.impl;

import com.example.backend.dto.request.SupplierDTO;
import com.example.backend.dto.response.SupplierRDTO;
import com.example.backend.enity.Supplier;
import com.example.backend.exception.SupplierNotFoundException;
import com.example.backend.mapstructs.SupplierMapper;
import com.example.backend.repository.SupplierRepository;
import com.example.backend.service.ISupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SupplierService implements ISupplierService {
    private final SupplierRepository supplierRepository;

    public SupplierService(SupplierRepository supplierRepository) {
        this.supplierRepository = supplierRepository;
    }
    @Autowired
    private RandomStringService randomStringService;
    @Override
    public List<SupplierRDTO> getAll() {
        List<Supplier> suppliers = supplierRepository.findAll();
        if (suppliers.size() == 0){
            throw new SupplierNotFoundException("Chưa có giá trị nào");
        }
        List<SupplierRDTO> supplierRDTOS = new ArrayList<>();
        for (Supplier std : suppliers){
            SupplierRDTO supplierRDTO = new SupplierRDTO();
            supplierRDTO.setName(std.getName());
            supplierRDTO.setNote(std.getNote());
            supplierRDTO.setStatus(std.isStatus());
            supplierRDTO.setCode(std.getCode());
            supplierRDTO.setEmail(std.getEmail());
            supplierRDTOS.add(supplierRDTO);
        }
        return supplierRDTOS;
    }

    @Override
    public void save(SupplierDTO supplier) {
        Supplier newSupplier = SupplierMapper.INSTANCE.supplierToSupplierDTO(supplier);
        newSupplier.setStatus(true);
        newSupplier.setCreatedAt(LocalDateTime.now());
        newSupplier.setCode(randomStringService.generateRandomString(5));
        supplierRepository.save(newSupplier);
    }

    @Override
    @Transactional
    public void update(SupplierDTO supplier, Long id) {
        Optional<Supplier> optionalSupplier = supplierRepository.findById(id);
        if (optionalSupplier.isEmpty()){
            throw new SupplierNotFoundException(" " +id);
        }
        Supplier oldSupplier = optionalSupplier.get();
        oldSupplier = SupplierMapper.INSTANCE.supplierToSupplierDTO(supplier);
    }
}
