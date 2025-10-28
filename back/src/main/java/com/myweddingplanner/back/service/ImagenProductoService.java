package com.myweddingplanner.back.service;

import com.myweddingplanner.back.dto.ImagenProductoDTO;
import com.myweddingplanner.back.model.ImagenProducto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public interface ImagenProductoService {

    Optional<ImagenProducto> findById(Long id);

    List<ImagenProducto> findAll();

    ImagenProducto save(ImagenProducto imagen);

    void deleteById (Long id);

    ImagenProductoDTO toDTO (ImagenProducto imagenProducto);
}
