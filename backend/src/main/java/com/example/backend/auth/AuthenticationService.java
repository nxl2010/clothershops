package com.example.backend.auth;

import com.example.backend.enity.Role;
import com.example.backend.enity.RoleName;
import com.example.backend.enity.User;
import com.example.backend.exception.DuplicateUsernameException;
import com.example.backend.jwt.JwtService;
import com.example.backend.repository.RoleRepository;
import com.example.backend.repository.UserRepository;
import com.example.backend.security.CustomUserDetails;
import com.example.backend.service.impl.RandomStringService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;
    @Autowired
    private RandomStringService.CustomerService customerService;
    public AuthenticationResponse register(RegisterRequest request){
        User user = new User();
        User ifUser = userRepository.findByUserName(request.getUserName());
        if(ifUser != null){
            throw new DuplicateUsernameException("Đã tồn ta người dùng");
        }
        //Lấy role trong database
        Role roleUser = roleRepository.findByName(RoleName.ROLE_USER)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy vai trò ROLE_USER"));

        //thêm user từ request
        user.setUserName(request.getUserName());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setFullName(request.getFullName());
        user.setEmail(request.getEmail());
        user.setRoles( Collections.singleton(roleUser));
        userRepository.save(user);
        customerService.save(request.getUserName());
        //build JWT từ user
        UserDetails customUserDetails = CustomUserDetails.mapUserToUserDetail(user);
        var jwtToken = jwtService.generateToken(customUserDetails);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }
    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        System.out.println(request.getUserName());
        System.out.println(request.getPassword());
        User user = userRepository.findByUserName(request.getUserName());
        if (user == null) {
            throw new UsernameNotFoundException("Không tồn tại người dùng");
        }
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getUserName(),
                            request.getPassword()
                    )
            );
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Sai tên đăng nhập hoặc mật khẩu");
        }


        UserDetails customUserDetails = CustomUserDetails.mapUserToUserDetail(user);
        var jwtToken = jwtService.generateToken(customUserDetails);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

}
