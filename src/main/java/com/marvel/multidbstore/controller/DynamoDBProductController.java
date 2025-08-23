package com.marvel.multidbstore.controller;

import com.marvel.multidbstore.entity.DynamoDBProduct;
import com.marvel.multidbstore.service.DynamoDBProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/dynamodb-products")
@RequiredArgsConstructor
public class DynamoDBProductController {
    private final DynamoDBProductService service;

    @PostMapping("/product")
    public DynamoDBProduct createProduct(@RequestBody DynamoDBProduct product) {
        return service.createProduct(product);
    }

    @GetMapping("/{id}")
    public DynamoDBProduct getProduct(@PathVariable String id) {
        return service.getProduct(id);
    }

    @GetMapping("/products")
    public List<DynamoDBProduct> getAllProducts() {
        return service.getAllProducts();
    }

    @PutMapping("/product")
    public DynamoDBProduct updateProduct(@RequestBody DynamoDBProduct product) {
        return service.updateProduct(product);
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable String id) {
        service.deleteProduct(id);
    }

    @DeleteMapping("/clear")
    public void clearAllProducts() {
        service.clearAllProducts();
    }
}
