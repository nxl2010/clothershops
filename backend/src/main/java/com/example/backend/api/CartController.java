package com.example.backend.api;

import com.example.backend.dto.request.CartDTO;
import com.example.backend.service.impl.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/api/v2/cart")
public class CartController {
    @Autowired
    private CartService cartService;
    @PostMapping
    public ResponseEntity<?> addCart(@RequestBody CartDTO cartDTO){
        cartService.save(cartDTO);
        return ResponseEntity.ok("OK");
    }
    @GetMapping
    public ResponseEntity<?> findAllByCustomer(@AuthenticationPrincipal Principal principal){
        return ResponseEntity.ok(cartService.findAllByCustomer(principal.getName()));
    }
}
