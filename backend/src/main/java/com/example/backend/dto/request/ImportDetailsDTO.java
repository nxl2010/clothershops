package com.example.backend.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ImportDetailsDTO {
    private String deliver;
    private List<QuantityProductDTO> quantityProductDTO;
    private double sumPrice;
}
