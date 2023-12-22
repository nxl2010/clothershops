package com.example.backend.repository;

import com.example.backend.enity.ImportDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImportDetailsRepository extends JpaRepository<ImportDetails,Long> {
    ImportDetails findByCode(String code);
}
