package com.example.backend.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShopImportDTO {
    private String name;
    private String userName;
    private String storeCode;
    private String importDetailsCode;
    private LocalDateTime createAt;
}
