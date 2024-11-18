package com.example.demo1.controllers;

import com.example.demo1.dtos.ProductDataDto;
import com.example.demo1.entities.Product;
import com.example.demo1.services.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {
    final private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/all")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public List<Product> getAllProducts () {
        return productService.fetchAllProducts();
    }

    @GetMapping("/{productId}")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<Product> getProductByProductId(@PathVariable long productId) {
        return productService.fetchProductById(productId);
    }

    @PostMapping("/create")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Product> createProduct(@RequestBody ProductDataDto productDataDto) {
        return productService.createNewProduct(productDataDto);
    }

    @PostMapping("/edit/{productId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Product> editProduct(@PathVariable int productId, @RequestBody ProductDataDto productDataDto) {
        return productService.updateProduct(productId, productDataDto);
    }

    @DeleteMapping("/delete/{productId}")
    @PreAuthorize("hasRole('ADMIN')")
    public long deleteProduct(@PathVariable long productId) {
        return productService.deleteProductByProductId(productId);
    }
}
