package com.example.backend.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO {
    private String shipCode;
    private boolean paymentMethod;
    private boolean isPaid;
    private double discount;
    private long totalAmount;
    private List<OrderdetailsDTO> orderdetailsDTOS;
}
