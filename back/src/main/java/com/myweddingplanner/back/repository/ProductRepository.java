package com.myweddingplanner.back.repository;

import com.myweddingplanner.back.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
