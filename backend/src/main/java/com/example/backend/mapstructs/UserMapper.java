package com.example.backend.mapstructs;

import com.example.backend.dto.UserDTO;
import com.example.backend.enity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import java.util.List;
@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);
    @Mapping(target = "id" ,ignore = true)
    User userDTOToUser(UserDTO userDTO);

    UserDTO userToUserDTO(User user);
    //        Convert: Put Delete
    @Mapping(target = "password", ignore = true)
    @Mapping(target = "userName", ignore = true)
    @Mapping(target = "id", ignore = true)

    void updateUserFromDTO(UserDTO userDTO, @MappingTarget User user);
    List<UserDTO> usersConvertUserDTOs(List<User> users);

}
