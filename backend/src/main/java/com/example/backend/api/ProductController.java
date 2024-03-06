package com.example.backend.api;

import com.example.backend.dto.request.ProductDTO;
import com.example.backend.dto.response.ProductDetailsDTO;
import com.example.backend.dto.response.ProductsOfCategory;
import com.example.backend.service.impl.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    @GetMapping("/test")
    public ResponseEntity<List<ProductsOfCategory>> test(){
        return new ResponseEntity<>(productService.findOfCategoryColor("fXvQD"),HttpStatus.OK);
    }
        @GetMapping("/detailproduct/{code}")
    public ResponseEntity<?> findDetailsProduct(@PathVariable String code){
        try {
            ProductDetailsDTO products = productService.findDetailsProduct(code);
            return new ResponseEntity<>(products, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
   //Lấy danh sách theo danh mục phân trang
    @GetMapping("/category/{code}")
    public ResponseEntity<Page<ProductsOfCategory>> getProductsByCategory(
            @PathVariable String code,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "8") int size) {

        try {
            Page<ProductsOfCategory> products = productService.findOfCategory(code, page, size);
            return new ResponseEntity<>(products, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
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
