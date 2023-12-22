package com.example.backend.mapstructs;

import com.example.backend.dto.response.RoleDTO;
import com.example.backend.enity.Role;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    RoleMapper INSTANCE = Mappers.getMapper(RoleMapper.class);

    RoleDTO toRole(Role role);
}