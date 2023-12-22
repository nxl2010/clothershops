package com.example.backend.api;

import com.example.backend.dto.response.RoleDTO;
import com.example.backend.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v2/role")
public class RoleController {
    @Autowired
    private RoleService roleService;
    @GetMapping
    public RoleDTO findAll(){
        return roleService.totrain();
    }
}
