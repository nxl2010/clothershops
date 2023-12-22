package com.example.backend.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MyUserDTO {
    private String fullName;
    private String avatar;
    private String address;
    private String phoneNumber;
    private String email;
}
