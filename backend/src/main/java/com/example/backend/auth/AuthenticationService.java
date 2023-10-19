package com.example.backend.auth;

import com.example.backend.enity.Role;
import com.example.backend.enity.RoleName;
import com.example.backend.enity.User;
import com.example.backend.jwt.JwtService;
import com.example.backend.repository.RoleRepository;
import com.example.backend.repository.UserRepository;
import com.example.backend.security.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;
    public AuthenticationResponse register(RegisterRequest request){
        User user = new User();
        //Lấy role trong database
        Role roleUser = roleRepository.findByName(RoleName.ROLE_USER)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy vai trò ROLE_USER"));

        //thêm user từ request
        user.setUserName(request.getUserName());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setFullName(request.getFullName());
        user.setRoles( Collections.singleton(roleUser));
        userRepository.save(user);
        //build JWT từ user
        UserDetails customUserDetails = CustomUserDetails.mapUserToUserDetail(user);
        var jwtToken = jwtService.generateToken(customUserDetails);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }
    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getUserName(),
                            request.getPassword()
                    )
            );
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Invalid username or password");
        }

        Optional<User> user = userRepository.findByUserName(request.getUserName());
        if (user.isPresent()) {
            throw new UsernameNotFoundException("user not found");
        }

        UserDetails customUserDetails = CustomUserDetails.mapUserToUserDetail(user.get());
        var jwtToken = jwtService.generateToken(customUserDetails);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

}
