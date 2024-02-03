package com.example.backend.service.impl;

import com.example.backend.dto.response.UserDTO;
import com.example.backend.dto.request.ChangePasswordDTO;
import com.example.backend.dto.request.MyUserDTO;
import com.example.backend.enity.Role;
import com.example.backend.enity.User;
import com.example.backend.exception.RoleNotFoundException;
import com.example.backend.mapstructs.UserMapper;
import com.example.backend.repository.RoleRepository;
import com.example.backend.repository.UserRepository;
import com.example.backend.service.IUserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UserService implements IUserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository,
                       RoleRepository roleRepository,
                       PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void save() {

    }

    @Override
    public List<UserDTO> findAll() {
        List<User> users = userRepository.findAll();
        return UserMapper.INSTANCE.usersToUserDTOs(users);
    }

    @Override
    public List<User> findAlll() {
        return userRepository.findAll();
    }

    @Override
    public void delete() {

    }

    @Override
    public UserDTO findByName(String username) {
        return UserMapper.INSTANCE.userToUserDTO(userRepository.findByUserName(username));
    }

    @Override
    @Transactional
    public User updateMyUser(String name, MyUserDTO newUser) {
        User user = userRepository.findByUserName(name);
        user.setFullName(newUser.getFullName());
        user.setEmail(newUser.getEmail());
        user.setAvatar(newUser.getAvatar());
        user.setPhoneNumber(newUser.getPhoneNumber());
        return user;
    }

    @Override
    @Transactional
    public User updateRole(String username, List<String> role) {
        User user = userRepository.findByUserName(username);
        Set<Role> roles = new HashSet<>();
        System.out.println(role.size());
        for (int i = 0; i < role.size() ; i++) {
            System.out.println(role.get(i));
            Optional<Role> optionalRole = roleRepository.findByCode(role.get(i));
            if (optionalRole.isEmpty()) {
                System.out.println(optionalRole.get());

                throw new RoleNotFoundException(role.get(i));
            } else {
                roles.add(optionalRole.get());
            }
        }
        user.setRoles(roles);
        return user;
    }

    @Override
    @Transactional
    public void changePassword(ChangePasswordDTO changePasswordDTO, String userName) {
        User user = userRepository.findByUserName(userName);
        if (!passwordEncoder.matches(changePasswordDTO.getOldPassword(), user.getPassword())) {
            throw new IllegalArgumentException("Mật khẩu cũ không đúng");
        }
        String newPasswordEncoded = passwordEncoder.encode(changePasswordDTO.getNewPassword());
        user.setPassword(newPasswordEncoded);
    }

    @Override
    public void deleteUser(String username) {
        User user = userRepository.findByUserName(username);
    }
}
