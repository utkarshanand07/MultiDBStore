package com.marvel.multidbstore.service;

import com.marvel.multidbstore.entity.DynamoDBProduct;
import com.marvel.multidbstore.repository.DynamoDBProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DynamoDBProductService {
    private final DynamoDBProductRepository repository;

    public DynamoDBProduct createProduct(DynamoDBProduct product) {
        return repository.save(product);
    }

    public DynamoDBProduct getProduct(String id) {
        return repository.findById(id);
    }

    public List<DynamoDBProduct> getAllProducts() {
        return repository.findAll();
    }

    public DynamoDBProduct updateProduct(DynamoDBProduct product) {
        return repository.save(product); // DynamoDB putItem replaces existing
    }

    public void deleteProduct(String id) {
        repository.deleteById(id);
    }

    public void clearAllProducts() {
        repository.deleteAll();
    }
}
