package com.example.backend.enity;

import jakarta.persistence.*;

@Entity
@Table(name = "export_details")
public class ExportDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "quanity")
    private long quanity;
    @Column(name = "price")
    private double price;
    @Column(name = "sum_price")
    private double sumPrice;
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Products product;
    @ManyToOne
    @JoinColumn(name = "size_id")
    private Size size;

}
