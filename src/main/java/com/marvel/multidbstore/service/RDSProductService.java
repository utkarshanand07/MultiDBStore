package com.marvel.multidbstore.service;

import com.marvel.multidbstore.entity.RDSProduct;
import com.marvel.multidbstore.repository.RDSProductRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RDSProductService {
    private final RDSProductRepository repository;

    @PersistenceContext
    private EntityManager entityManager;

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

    public RDSProduct deleteProduct(Long id) {
        RDSProduct product = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        repository.delete(product);
        return product;
    }

    @Value("${rds.table.name}")
    private String tableName;
    @Transactional
    public void clearAllProducts() {
        String query = "TRUNCATE TABLE " + tableName;
        entityManager.createNativeQuery(query).executeUpdate();
    }
}
