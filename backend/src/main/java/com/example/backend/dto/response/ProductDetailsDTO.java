package com.example.backend.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDetailsDTO {
    private String name;
    private String url;
    private long price;
    private String code;
    private List<String> sizes;
    private long totalQuantity;
}
