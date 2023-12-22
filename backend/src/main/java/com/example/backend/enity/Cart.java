package com.example.backend.enity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Cart")
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
//    @ManyToOne
//    @JoinColumn(name = "customer_id", nullable = false)
//    private Customer customer;
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Products product;
    @ManyToOne
    @JoinColumn(name = "size_id")
    private Size size;
    @Column(name = "quantity")
    private long quantity;
}
