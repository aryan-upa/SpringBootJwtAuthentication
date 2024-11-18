package com.example.demo1.entities;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Table (name = "products")
@Entity
public class Product {
    @Id
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long productId;

    @Column(nullable = false, length = 50)
    private String productName;

    @Column(nullable = false, length = 300)
    private String productDescription;

    @Column(nullable = false)
    private int quantity;

    @Column(nullable = false)
    private double price;
}
