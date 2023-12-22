package com.example.backend.mapstructs;

import com.example.backend.dto.response.UserDTO;
import com.example.backend.enity.Role;
import com.example.backend.enity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mapping(target = "rolesDto", source = "roles", qualifiedByName = "roleToStringSet")
    UserDTO userToUserDTO(User user);

    @Named("roleToStringSet")
    default Set<String> roleSetToStringSet(Set<Role> roles) {
        if (roles == null) {
            return null;
        }
        return roles.stream()
                .map(role -> role.getCode()) // Chuyển từ Role sang String (lấy thuộc tính code)
                .collect(Collectors.toSet());
    }

    List<UserDTO> usersToUserDTOs(List<User> users);
}

