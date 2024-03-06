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
@Table(name = "products")
public class Products extends AbstractEnity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "code")
    private String code;
    @Column(name = "name")
    private String name;
    @Column(name = "imageurl")
    private String imageUrl;
    @Column(name = "after_image")
    private String afterImage;
    @Column(name = "short_description")
    private String shortDescription;
    @Column(name = "is_new")
    private boolean isNew;
    @Column(name = "is_featured")
    private boolean isFeatured;
    @Column(name = "color")
    private String color;
    @ElementCollection
    @CollectionTable(name = "prices", joinColumns = @JoinColumn(name = "products_id"))
    @Column(name = "price")
    private List<Long> prices;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "supplier_id")
    private Supplier supplier;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "category_id")
    private Category category;

}
