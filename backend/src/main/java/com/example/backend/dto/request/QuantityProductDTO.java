package com.example.backend.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuantityProductDTO {
    private String productCode;
    private Map<String, Long> sizeQuantities;
    private double price;
}
