package com.myweddingplanner.back.service;

import com.myweddingplanner.back.dto.AlergenoDTO;

import java.util.List;
import java.util.Optional;

public interface AlergenoService {

    Optional<AlergenoDTO> findById(Long id);

    List<AlergenoDTO> findAll();

    AlergenoDTO save(AlergenoDTO dto);

    void deleteById (Long id);
}
