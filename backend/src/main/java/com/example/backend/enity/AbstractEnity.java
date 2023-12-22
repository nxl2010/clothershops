package com.example.backend.enity;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.time.LocalDateTime;
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@AllArgsConstructor
@Data
@NoArgsConstructor
public abstract class AbstractEnity {
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "created_by", nullable = false, updatable = false)
    private String createdBy;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "updated_by")
    private String updated_by;
    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
        // Đây là nơi để lấy thông tin về người tạo, bạn có thể thay đổi tùy thuộc vào nhu cầu
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        this.createdBy = authentication.getName();
    }

}
