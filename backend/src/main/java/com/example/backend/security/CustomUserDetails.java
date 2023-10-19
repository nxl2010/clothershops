package com.example.backend.security;

import com.example.backend.enity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomUserDetails implements UserDetails {
    //Khai báo các fields trong userdetails
    private Long id;
    private String username;
    private String password;
    private Collection<? extends GrantedAuthority> authorities;
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }
    public static CustomUserDetails mapUserToUserDetail(User user){
        //Lấy role của user.
        List<GrantedAuthority> authorityList = user.getRoles().stream()
                .map(roles -> new SimpleGrantedAuthority(roles.getName().name()))
                .collect(Collectors.toList());

        return new CustomUserDetails(
                user.getId(),
                user.getUserName(),
                user.getPassword(),
                authorityList
        );
    }
    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
