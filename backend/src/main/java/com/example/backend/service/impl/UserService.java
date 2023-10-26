package com.example.backend.service.impl;

import com.example.backend.dto.UserDTO;
import com.example.backend.repository.UserRepository;
import com.example.backend.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements IUserService {
    @Autowired
    private UserRepository userRepository;


    @Override
    public void save() {

    }

    @Override
    public List<UserDTO> findAll() {
        return null;
    }

    @Override
    public void delete() {

    }
}
