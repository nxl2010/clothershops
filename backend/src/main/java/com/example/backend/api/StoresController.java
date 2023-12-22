package com.example.backend.api;

import com.example.backend.enity.Stores;
import com.example.backend.service.impl.StoresService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v2/stores")
public class StoresController {
    @Autowired
    private StoresService storesService;
    @GetMapping
    public List<Stores> findAll(){
        return storesService.findAll();
    }
}
