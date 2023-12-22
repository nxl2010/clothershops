package com.example.backend.api;

import com.example.backend.dto.request.SupplierDTO;
import com.example.backend.service.impl.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v2/supplier")
public class SupplierController {
    @Autowired
    private SupplierService supplierService;
    @GetMapping
    public ResponseEntity<?> getAllSupplier(){
        try{
            return new ResponseEntity<>(supplierService.getAll(), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.OK);
        }
    }

    @PostMapping
    public ResponseEntity<?> addSupplier(@RequestBody SupplierDTO supplier){
            supplierService.save(supplier);
            return new ResponseEntity<>("OK", HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<?> updateSupplier(@RequestBody SupplierDTO supplierDTO,@PathVariable Long id){
            supplierService.update(supplierDTO, id);
            return new ResponseEntity<>("OK",HttpStatus.OK);
    }

}
