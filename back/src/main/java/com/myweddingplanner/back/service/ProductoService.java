package com.myweddingplanner.back.service;

import com.myweddingplanner.back.dto.ProductoDTO;
import com.myweddingplanner.back.model.Producto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface ProductoService {

    Optional<ProductoDTO> findById(Long id);

    List<ProductoDTO> findAll();

    ProductoDTO save(ProductoDTO dto);

    void deleteById (Long id);

    ProductoDTO toDTO(Producto producto);

}
