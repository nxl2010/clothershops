package com.example.backend.api;

import com.example.backend.dto.request.ShopImportDTO;
import com.example.backend.dto.response.ShopImportRDTO;
import com.example.backend.service.impl.ImportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v2/shopimports")
public class ShopImportController {
    @Autowired
    private ImportService importService;
    @PostMapping
    public ResponseEntity<?> addShopImports(@RequestBody  ShopImportDTO shopImportDTO){
        importService.addShopImportDTO(shopImportDTO);
        return ResponseEntity.ok("OK");
    }
    @GetMapping
    public ResponseEntity<?> findShopImport(){
        try {
            List<ShopImportRDTO> imports = importService.findAll();
            return new ResponseEntity<>(imports, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
