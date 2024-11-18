package com.example.demo1.dtos;

import lombok.Data;

@Data
public class ProductDataDto {
    private long productId;
    private String productName;
    private String productDescription;
    private double productPrice;
    private int productQuantity;
}
