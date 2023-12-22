package com.example.backend.api;

import com.example.backend.dto.response.UserDTO;
import com.example.backend.dto.request.ChangePasswordDTO;
import com.example.backend.dto.request.MyUserDTO;
import com.example.backend.enity.User;
import com.example.backend.exception.RoleNotFoundException;
import com.example.backend.service.impl.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/v2/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<UserDTO> findAll(){
        return userService.findAll();
    }

    @GetMapping("/myuser")
    public ResponseEntity<?> getMyUser(Principal principal) {
        if (principal != null) {
            String username = principal.getName();
            return ResponseEntity.status(HttpStatus.OK).body(userService.findByName(username));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Chưa đăng nhập");
        }
    }
    @PutMapping("/myuser")
    public ResponseEntity<User> updateMyUser(Principal principal, @RequestBody MyUserDTO newUser) {
        User updatedUser = userService.updateMyUser(principal.getName(), newUser);
        if (updatedUser != null) {
            return new ResponseEntity<>(updatedUser, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @PutMapping("/updateRole/{username}")
    public ResponseEntity<?> updateRole(@PathVariable String username, @RequestBody List<String> role) {
        try {
            User updatedUser = userService.updateRole(username, role);
            return new ResponseEntity<>(updatedUser, HttpStatus.OK);
        } catch (RoleNotFoundException e) {
            return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PutMapping("/changepassword")
    public ResponseEntity<?> changePassword(Principal principal,
                                            @RequestBody ChangePasswordDTO changePasswordDTO){
        String userName = principal.getName();

        try{
            userService.changePassword(changePasswordDTO, userName);
            return new ResponseEntity<>("SUCCESS",HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

    }
    @DeleteMapping("/{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable String username) {
        try {
            userService.deleteUser(username);
            return ResponseEntity.ok("Người dùng đã được xóa");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Lỗi hệ thống");
        }
    }
}
