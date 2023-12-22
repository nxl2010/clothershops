package com.example.backend.enity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cost {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "standard_cost")
    private double standardCost;
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Products product;
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;
    @Column(name = "created_by", nullable = false, updatable = false)
    private String createdBy;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
        // Đây là nơi để lấy thông tin về người tạo, bạn có thể thay đổi tùy thuộc vào nhu cầu
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        this.createdBy = authentication.getName();
    }
}
