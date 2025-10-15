package com.myweddingplanner.back.service;

import com.myweddingplanner.back.dto.BodaDTO;
import com.myweddingplanner.back.model.Alergeno;
import com.myweddingplanner.back.model.Boda;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public interface BodaService {

    Optional<BodaDTO> findById(Long id);

    List<BodaDTO> findAll();

    BodaDTO save(BodaDTO dto);

    void deleteById (Long id);
}
