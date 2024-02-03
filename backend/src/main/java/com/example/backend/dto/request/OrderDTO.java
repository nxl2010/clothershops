package com.example.backend.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO {
    private String shippingAddress;
    private String billingAddress;
    private String shippingMethod;
    private String paymentMethod;
    private double discount;
    private long totalAmount;
    private List<OrderdetailsDTO> orderdetailsDTOS;
}
