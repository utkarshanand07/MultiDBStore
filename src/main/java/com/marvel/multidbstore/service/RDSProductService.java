package com.marvel.multidbstore.service;

import com.marvel.multidbstore.entity.RDSProduct;
import com.marvel.multidbstore.repository.RDSProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RDSProductService {
    private final RDSProductRepository repository;

    public RDSProduct createProduct(RDSProduct product) {
        return repository.save(product);
    }

    public RDSProduct getProduct(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));
    }

    public List<RDSProduct> getAllProducts() {
        return repository.findAll();
    }

    public RDSProduct updateProduct(RDSProduct product) {
        if (!repository.existsById(product.getId())) {
            throw new RuntimeException("Product not found");
        }
        return repository.save(product);
    }

    public void deleteProduct(Long id) {
        repository.deleteById(id);
    }

    public void clearAllProducts() {
        repository.deleteAll();
    }
}
