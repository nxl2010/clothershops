package com.example.backend.dto.response;

import com.example.backend.enity.OrderShip;
import com.example.backend.enity.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderRDTO {
    private String name;
    private String customerName;
    private String sdt;
    private String shippingAddress;
    private long totalAmount;
   private LocalDateTime time;
    private OrderStatus orderStatus;
    private OrderShip orderShip;
}
