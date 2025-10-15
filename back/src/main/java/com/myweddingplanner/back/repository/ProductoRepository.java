package com.myweddingplanner.back.repository;

import com.myweddingplanner.back.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoRepository extends JpaRepository<Producto, Long> {
}
