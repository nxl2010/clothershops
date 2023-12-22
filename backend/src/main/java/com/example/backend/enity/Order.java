package com.example.backend.enity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "code")
    private String code;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @Column(name = "order_date", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime orderDate;

    @Column(name = "status", nullable = false)
    private String status;

    @Column(name = "total_amount", nullable = false)
    private long totalAmount;

    @Column(name = "payment_status", nullable = false)
    private String paymentStatus;

    @Column(name = "shipping_address", nullable = false)
    private String shippingAddress;

    @Column(name = "billing_address", nullable = false)
    private String billingAddress;

    @Column(name = "payment_method", nullable = false)
    private String paymentMethod;

    @Column(name = "shipping_method", nullable = false)
    private String shippingMethod;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderDetails> orderItems;

}