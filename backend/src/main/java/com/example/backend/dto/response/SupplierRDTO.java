package com.example.backend.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SupplierRDTO {
    private String name;
    private String address;
    private String email;
    private boolean status;
    private String note;
    private String code;
}
