package com.example.backend.dto;

import com.example.backend.enity.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private Long id;
    private String fullName;
    private String avatar;
    private String email;
    private String phoneNumber;
    private boolean status;
    private Set<Role> roles;
}
