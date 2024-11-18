package com.example.demo1.services;

import com.example.demo1.dtos.ProductDataDto;
import com.example.demo1.entities.Product;
import com.example.demo1.repositories.ProductRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    final private ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> fetchAllProducts() {
        return productRepository.findAll();
    }

    public ResponseEntity<Product> fetchProductById(long productId) {
        Product product = productRepository.findById(productId).orElseThrow(() -> new RuntimeException("Product with ID : " + productId + " not found!"));
        return ResponseEntity.ok(product);
    }

    public ResponseEntity<Product> createNewProduct(ProductDataDto productDataDto) {
        Product product = new Product();

        product.setProductName(productDataDto.getProductName());
        product.setProductDescription(productDataDto.getProductDescription());
        product.setPrice(productDataDto.getProductPrice());
        product.setQuantity(productDataDto.getProductQuantity());

        return ResponseEntity.ok(productRepository.save(product));
    }

    public ResponseEntity<Product> updateProduct(long productId, ProductDataDto productDataDto) {
        Product product = productRepository.findByProductId(productId).orElseThrow(() -> new RuntimeException("Product with ID : " + productId + " not found!"));

        product.setProductId(productId);
        product.setProductName(productDataDto.getProductName());
        product.setProductDescription(productDataDto.getProductDescription());
        product.setPrice(productDataDto.getProductPrice());
        product.setQuantity(productDataDto.getProductQuantity());

        return ResponseEntity.ok(productRepository.save(product));
    }

    public long deleteProductByProductId(long productId) {
        try {
            productRepository.deleteById(productId);
            return productId;
        } catch (Exception e) {
            return -1L;
        }
    }
}
