package com.example.backend.repository;

import com.example.backend.enity.ShopImports;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ImportsRepository extends JpaRepository<ShopImports, Long> {
    Optional<ShopImports> findByStores_Id(Long storesId);
}
