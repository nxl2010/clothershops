package com.example.backend.api;

import com.example.backend.dto.request.ProductDTO;
import com.example.backend.service.impl.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v2/product")
public class ProductController {
    @Autowired
    private ProductService productService;
    @GetMapping
    public ResponseEntity<?> getAllProducts(){
        try{
            return new ResponseEntity<>(productService.findAll(), HttpStatus.OK);
        }catch (Exception e){
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
    @PostMapping
    public ResponseEntity<?> addProduct(@RequestBody ProductDTO productDTO){
        try{
            productService.save(productDTO);
            return new ResponseEntity<>(productDTO, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/supplier")
    public ResponseEntity<?> findBySupplierId(@RequestParam Long supplierId){
        try{
            return new ResponseEntity<>(productService.findBySupplierId(supplierId), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
