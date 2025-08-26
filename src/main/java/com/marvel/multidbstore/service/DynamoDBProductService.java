package com.marvel.multidbstore.service;

import com.marvel.multidbstore.entity.DynamoDBProduct;
import com.marvel.multidbstore.repository.DynamoDBProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DynamoDBProductService {
    private final DynamoDBProductRepository repository;

    public DynamoDBProduct createProduct(DynamoDBProduct product) {
        product.setId(UUID.randomUUID().toString()); // Auto-generate ID
        return repository.save(product);
    }

    public DynamoDBProduct getProduct(String id) {
        DynamoDBProduct product = repository.findById(id);
        if (product == null) {
            throw new RuntimeException("Product not found");
        }
        return product;
    }

    public List<DynamoDBProduct> getAllProducts() {
        return repository.findAll();
    }

    public DynamoDBProduct updateProduct(DynamoDBProduct product) {
        if (repository.findById(product.getId()) == null) {
            throw new RuntimeException("Product not found");
        }
        return repository.save(product); // putItem replaces if exists
    }

    public DynamoDBProduct deleteProduct(String id) {
        DynamoDBProduct product = repository.findById(id);
        if (product == null) {
            throw new RuntimeException("Product not found");
        }
        repository.deleteById(id);
        return product;
    }

    public void clearAllProducts() {
        repository.deleteAll();
    }
}
