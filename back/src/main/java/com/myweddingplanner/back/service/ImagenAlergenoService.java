package com.myweddingplanner.back.service;

import com.myweddingplanner.back.dto.ImagenAlergenoDTO;
import com.myweddingplanner.back.model.ImagenAlergeno;

import java.util.List;
import java.util.Optional;

public interface ImagenAlergenoService {

    Optional<ImagenAlergeno> findById(Long id);

    List<ImagenAlergeno> findAll();

    ImagenAlergeno save(ImagenAlergeno imagen);

    void deleteById (Long id);

    ImagenAlergenoDTO toDTO (ImagenAlergeno imagenAlergeno);
}
