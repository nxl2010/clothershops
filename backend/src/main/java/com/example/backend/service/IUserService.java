package com.example.backend.service;

import com.example.backend.dto.response.UserDTO;
import com.example.backend.dto.request.ChangePasswordDTO;
import com.example.backend.dto.request.MyUserDTO;
import com.example.backend.enity.User;

import java.util.List;

public interface IUserService {
    void save();
    List<UserDTO> findAll();
    List<User> findAlll();
    void delete();

    UserDTO findByName(String username);

    User updateMyUser(String name, MyUserDTO newUser);

    User updateRole(String username, List<String> role);

    void changePassword(ChangePasswordDTO changePasswordDTO, String userName);

    void deleteUser(String username);
}
