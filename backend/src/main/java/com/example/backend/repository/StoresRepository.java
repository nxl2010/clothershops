package com.example.backend.repository;

import com.example.backend.enity.Stores;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface StoresRepository extends JpaRepository<Stores,Long> {
    Optional<Stores> findByName(String name);
    Stores findByCode(String code);
}
