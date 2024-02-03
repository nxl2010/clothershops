package com.example.backend.enity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "shop_exports")
public class ShopExports {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    private String name;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @Column(name = "created_at")
    private LocalDateTime createdAt;

}
