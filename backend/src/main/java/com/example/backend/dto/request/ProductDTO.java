package com.example.backend.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class ProductDTO {
    private String name;
    private String imageUrl;
    private String shortDescription;
    private String supplierCode;
}
