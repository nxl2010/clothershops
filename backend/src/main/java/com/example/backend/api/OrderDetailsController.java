package com.example.backend.api;

import com.example.backend.dto.request.OrderdetailsDTO;
import com.example.backend.service.impl.OrderDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v2/orderdetails")
public class OrderDetailsController {
    @Autowired
    private OrderDetailsService orderDetailsService;
    @PostMapping
    public ResponseEntity<?> addOrderDetails(@RequestBody OrderdetailsDTO orderdetailsDTO){
            orderDetailsService.save(orderDetailsService);
            return ResponseEntity.ok("OK");
    }

}
