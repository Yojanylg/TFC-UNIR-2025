package com.myweddingplanner.back.service;

import com.myweddingplanner.back.dto.AlergenoDTO;
import com.myweddingplanner.back.model.Alergeno;

import java.util.List;
import java.util.Optional;

public interface AlergenoService {

    Optional<AlergenoDTO> findById(Long id);

    List<AlergenoDTO> findAll();

    AlergenoDTO save(AlergenoDTO dto);

    void deleteById(Long id);

    AlergenoDTO toDTO(Alergeno alergeno);

}
