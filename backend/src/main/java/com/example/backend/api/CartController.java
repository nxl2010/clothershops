package com.example.backend.api;

import com.example.backend.dto.request.CartDTO;
import com.example.backend.service.impl.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v2/cart")
public class CartController {
    @Autowired
    private CartService cartService;
    @PostMapping
    public ResponseEntity<?> addCart(@RequestBody CartDTO cartDTO, @RequestParam String username){
        cartService.save(cartDTO, username);
        return ResponseEntity.ok("OK");
    }
    @GetMapping
    public ResponseEntity<?> findAllByCustomer( ){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return ResponseEntity.ok(cartService.findAllByCustomer(authentication.getName()));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCart(@PathVariable("id") Long id) {
       cartService.deleteCart(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
