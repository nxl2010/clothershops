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
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "code")
    private String code;

    @Column(name = "name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;
    @ManyToOne
    @JoinColumn(name = "employee_id")
    private User user;

    @Column(name = "order_date")
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime orderDate;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private OrderStatus status;

    @Column(name = "discount")
    private double discount;
    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private OrderShip shipStatus;

    @Column(name = "total_amount")
    private long totalAmount;

    @Column(name = "customer_name")
    private String customer_name;

    @Column(name = "sdt")
    private String sdt;

    @Column(name = "shipping_address")
    private String shippingAddress;

    @Column(name = "payment_method", nullable = false)
    private boolean paymentMethod;


}