package com.example.backend.api;

import com.example.backend.dto.request.OrderDTO;
import com.example.backend.service.impl.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v2/order")
public class OrderController {
    @Autowired
    private OrderService orderService;
    @PostMapping
    public ResponseEntity<?> addOrder(OrderDTO orderDTO){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        orderService.save(orderDTO, authentication.getName());
        return ResponseEntity.ok("ok");
    }
}
