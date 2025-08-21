package com.marvel.multidbstore.repository;

import com.marvel.multidbstore.entity.RDSProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RDSProductRepository extends JpaRepository<RDSProduct, Long> {}
