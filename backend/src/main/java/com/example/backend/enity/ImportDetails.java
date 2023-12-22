package com.example.backend.enity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "import_details")
public class ImportDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "code")
    private String code;
    @Column(name = "deliver")
    private String deliver;
    @Column(name = "sum_price")
    private double sumPrice;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "import_details_id")
    private Set<ProductSizeQuantity> productSizeQuantities;
    @OneToOne
    @JoinColumn(name = "shop_import_id")
    private ShopImports shopImport;

//    @Column(name = "created_at", nullable = false, updatable = false)
//    private LocalDateTime createdAt;
//
//    @Column(name = "creator", nullable = false, updatable = false)
//    private String creator;

}
