package com.example.backend.repository;

import com.example.backend.enity.SuggestClother;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SuggestClotherRepository extends JpaRepository<SuggestClother, Long> {
    Optional<SuggestClother> findByMainCategory(String mainCategory);
}
