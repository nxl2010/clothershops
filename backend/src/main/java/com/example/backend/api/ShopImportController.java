package com.example.backend.api;

import com.example.backend.dto.request.ShopImportDTO;
import com.example.backend.service.impl.ImportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
