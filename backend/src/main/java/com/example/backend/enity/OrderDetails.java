package com.example.backend.enity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "order_details")
public class OrderDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Products product;
    @ManyToOne
    @JoinColumn(name = "size_id")
    private Size size;
    @Column(name = "quantity")
    private long quantity;
    @Column(name = "unit_price")
    private double unitPrice;
    @Column(name = "discount")
    private float discount;
    @Column(name = "total_amount")
    private long totalAmount;
    @Column(name = "status", length = 50)
    private String status;
    @Column(name = "datetime")
    private LocalDateTime dateTime;
}
