package com.marvel.multidbstore.controller;

import com.marvel.multidbstore.entity.RDSProduct;
import com.marvel.multidbstore.repository.RDSProductRepository;
import com.marvel.multidbstore.responsehandler.ApiResponse;
import com.marvel.multidbstore.service.RDSProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rds-products")
@RequiredArgsConstructor
public class RDSProductController {
    private final RDSProductService service;

    @PostMapping("/product")
    public ResponseEntity<ApiResponse<RDSProduct>> createProduct(@RequestBody RDSProduct product) {
        RDSProduct savedProduct = service.createProduct(product);
        return ResponseEntity.ok(new ApiResponse<>("Product added successfully", savedProduct));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<RDSProduct>> getProduct(@PathVariable Long id) {
        RDSProduct product = service.getProduct(id);
        return ResponseEntity.ok(new ApiResponse<>("Product fetched successfully", product));
    }

    @GetMapping("/products")
    public ResponseEntity<ApiResponse<List<RDSProduct>>> getAllProducts() {
        List<RDSProduct> products = service.getAllProducts();
        return ResponseEntity.ok(new ApiResponse<>("Products fetched successfully", products));
    }

    @PutMapping("/product")
    public ResponseEntity<ApiResponse<RDSProduct>> updateProduct(@RequestBody RDSProduct product) {
        RDSProduct updatedProduct = service.updateProduct(product);
        return ResponseEntity.ok(new ApiResponse<>("Product updated successfully", updatedProduct));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<RDSProduct>> deleteProduct(@PathVariable Long id) {
        RDSProduct deletedProduct = service.deleteProduct(id);
        return ResponseEntity.ok(new ApiResponse<>("Product deleted successfully", deletedProduct));
    }

    @DeleteMapping("/clear")
    public ResponseEntity<ApiResponse<String>> clearAllProducts() {
        service.clearAllProducts();
        return ResponseEntity.ok(new ApiResponse<>("Deleted all products successfully", null));
    }
}
