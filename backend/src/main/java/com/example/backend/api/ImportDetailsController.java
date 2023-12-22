package com.example.backend.api;

import com.example.backend.dto.request.ImportDetailsDTO;
import com.example.backend.enity.ImportDetails;
import com.example.backend.service.impl.ImportDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v2/importdetails")
public class ImportDetailsController {
    @Autowired
    private ImportDetailsService importDetailsService;
    @PostMapping
    public ResponseEntity<?> addImportDetails(@RequestBody ImportDetailsDTO importDetailsDTO){
         importDetailsService.save(importDetailsDTO);
         return ResponseEntity.ok("Ok");
    }
    @GetMapping
    public ResponseEntity<ImportDetails> findAll(){
        return ResponseEntity.ok(importDetailsService.findAll());
    }
}
