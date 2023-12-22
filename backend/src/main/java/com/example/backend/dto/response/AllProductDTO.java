package com.example.backend.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class AllProductDTO {

    private String code;
    private String name;
    private String supplier;
    private String category;
    private double price;
    private double quantity;

}
