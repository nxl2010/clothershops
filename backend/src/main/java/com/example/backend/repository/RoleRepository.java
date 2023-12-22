package com.example.backend.repository;

import com.example.backend.enity.Role;
import com.example.backend.enity.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
  Optional<Role> findByName(RoleName roleName);
  Optional<Role> findByCode(String code);
}
