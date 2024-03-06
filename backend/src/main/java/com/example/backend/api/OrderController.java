package com.example.backend.api;

import com.example.backend.dto.request.OrderDTO;
import com.example.backend.enity.OrderShip;
import com.example.backend.service.impl.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v2/order")
public class OrderController {
    @Autowired
    private OrderService orderService;
//    Thêm hàng hóa mới
    @PostMapping
    public ResponseEntity<?> addOrder(@RequestBody  OrderDTO orderDTO){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
       try {

           return ResponseEntity.ok(orderService.save(orderDTO, authentication.getName()));
       } catch (Exception e){
           return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
       }

    }
    //Cập nhật trạng thái thanh toán của người dùng
    @PutMapping("/pay/{code}")
    public ResponseEntity<?> updatePay(@PathVariable String code, @RequestParam Boolean isPaid ){
        orderService.updatePay(isPaid, code);
        return ResponseEntity.ok("ok");
    }
    // cập nhật trạng thái vận chyển của admin
    @PutMapping("/ship/{code}")
    public ResponseEntity<?> updateShip(@PathVariable String code, @RequestBody OrderShip orderShip ){
        orderService.updateShip(orderShip, code);
        return ResponseEntity.ok("ok");
    }
    //Lấy da tất cả tất cả đơn hàng
    @GetMapping
    public ResponseEntity<?> findAllByCustomerCode(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return ResponseEntity.ok(orderService.findAllByCustomerCode(authentication.getName()));
    }
    //Lấy ra đơn hàng theo trạng thái
    @GetMapping("/shipstatus")
    public ResponseEntity<?> findByCustomerCodeAndShipStatus(@RequestParam OrderShip status) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        try {
            return ResponseEntity.ok(orderService.findBYCustomerCodeAndOrderShip(authentication.getName(), status));
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    //Lấy ra tất cả các đơn cho admin
    @PreAuthorize("hasRole('ROLE_MANGER')")
    @GetMapping("/admin")
    public ResponseEntity<?> findAllForAdmin(){
        return ResponseEntity.ok(orderService.findAllForAdmin());
    }
}
