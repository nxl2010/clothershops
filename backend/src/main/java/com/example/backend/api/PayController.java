package com.example.backend.api;

import com.example.backend.service.VNPayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v2/payment")
public class PayController {
    @Autowired
    private VNPayService vnPayService;
    @PostMapping
    public ResponseEntity<?> creatPayment(@RequestParam("amount") int orderTotal,
                                          @RequestParam("orderInfo") String orderInfo,
                                          @RequestParam ("code") String code){
        String url = "http://localhost:5173";
        return ResponseEntity.ok(vnPayService.createOrder(orderTotal,orderInfo, code, url));
    }
}
