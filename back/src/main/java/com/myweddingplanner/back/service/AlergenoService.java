package com.myweddingplanner.back.service;

import com.myweddingplanner.back.dto.AlergenoDTO;
import com.myweddingplanner.back.dto.AlergenoRUDTO;
import com.myweddingplanner.back.model.Alergeno;

import java.util.List;
import java.util.Optional;

public interface AlergenoService {

    Optional<Alergeno> findById(Long id);

    List<Alergeno> findAll();

    AlergenoDTO save(AlergenoDTO dto);

    void deleteById (Long id);

    AlergenoRUDTO toRUDTO(Alergeno alergeno);


}
