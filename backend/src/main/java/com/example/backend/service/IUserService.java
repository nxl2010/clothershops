package com.example.backend.service;

import com.example.backend.dto.UserDTO;

import java.util.List;

public interface IUserService {
    void save();
    List<UserDTO> findAll();
    void delete();
}
