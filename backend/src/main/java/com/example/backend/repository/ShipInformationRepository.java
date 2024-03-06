package com.example.backend.repository;

import com.example.backend.enity.ShipInformation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ShipInformationRepository extends JpaRepository<ShipInformation, Long> {
    List<ShipInformation> findByCustomerCode(String code);
    Optional<ShipInformation> findByCode(String code);
    Optional<ShipInformation> findTopByCustomerCodeOrderByIdDesc(String code);
}
