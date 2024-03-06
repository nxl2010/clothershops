package com.example.backend.enity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "suggest_clother")
public class SuggestClother {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "main_category")
    private String mainCategory;
    @ElementCollection
    @CollectionTable(name = "subCategory", joinColumns = @JoinColumn(name = "suggestc_id"))
    @Column(name = "subCategory")
    private List<String> subCategory;
}
