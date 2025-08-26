package com.marvel.multidbstore.controller;

import com.marvel.multidbstore.entity.DynamoDBProduct;
import com.marvel.multidbstore.responsehandler.ApiResponse;
import com.marvel.multidbstore.service.DynamoDBProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/dynamodb-products")
@RequiredArgsConstructor
public class DynamoDBProductController {
    private final DynamoDBProductService service;

    @PostMapping("/product")
    public ResponseEntity<ApiResponse<DynamoDBProduct>> createProduct(@RequestBody DynamoDBProduct product) {
        DynamoDBProduct savedProduct = service.createProduct(product);
        return ResponseEntity.ok(new ApiResponse<>("Product added successfully", savedProduct));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<DynamoDBProduct>> getProduct(@PathVariable String id) {
        DynamoDBProduct product = service.getProduct(id);
        return ResponseEntity.ok(new ApiResponse<>("Product fetched successfully", product));
    }

    @GetMapping("/products")
    public ResponseEntity<ApiResponse<List<DynamoDBProduct>>> getAllProducts() {
        List<DynamoDBProduct> products = service.getAllProducts();
        return ResponseEntity.ok(new ApiResponse<>("Products fetched successfully", products));
    }

    @PutMapping("/product")
    public ResponseEntity<ApiResponse<DynamoDBProduct>> updateProduct(@RequestBody DynamoDBProduct product) {
        DynamoDBProduct updatedProduct = service.updateProduct(product);
        return ResponseEntity.ok(new ApiResponse<>("Product updated successfully", updatedProduct));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<DynamoDBProduct>> deleteProduct(@PathVariable String id) {
        DynamoDBProduct deletedProduct = service.deleteProduct(id);
        return ResponseEntity.ok(new ApiResponse<>("Product deleted successfully", deletedProduct));
    }

    @DeleteMapping("/clear")
    public ResponseEntity<ApiResponse<String>> clearAllProducts() {
        service.clearAllProducts();
        return ResponseEntity.ok(new ApiResponse<>("Deleted all products successfully", null));
    }
}
