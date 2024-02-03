package com.example.backend.service.impl;

import com.example.backend.dto.response.RoleDTO;
import com.example.backend.enity.Role;
import com.example.backend.enity.RoleName;
import com.example.backend.mapstructs.RoleMapper;
import com.example.backend.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoleService {
    @Autowired
    private RoleRepository roleRepository;
    public RoleDTO totrain(){
        Optional<Role> role= roleRepository.findByName(RoleName.ROLE_MANGER);
        return RoleMapper.INSTANCE.toRole(role.get());
    }
}
