package com.marvel.multidbstore.controller;

import com.marvel.multidbstore.entity.RDSProduct;
import com.marvel.multidbstore.repository.RDSProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rds-products")
public class RDSProductController {
    @Autowired
    private RDSProductRepository repo;

    @PostMapping("/product")
    public RDSProduct save(@RequestBody RDSProduct product) {
        return repo.save(product);
    }

    @GetMapping("/products")
    public List<RDSProduct> getAll() {
        return repo.findAll();
    }
}
