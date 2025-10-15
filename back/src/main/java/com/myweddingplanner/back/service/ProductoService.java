package com.myweddingplanner.back.service;

import com.myweddingplanner.back.model.Producto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface ProductoService {

    Optional<Producto> findById(Long id);

    List<Producto> findAll();

    Producto save(Producto producto);

    void deleteById (Long id);

}
