package com.myweddingplanner.back.service;

import com.myweddingplanner.back.dto.BodaDTO;
import com.myweddingplanner.back.dto.RegisterRequest;
import com.myweddingplanner.back.model.Boda;
import com.myweddingplanner.back.model.Usuario;

import java.util.List;
import java.util.Optional;

public interface BodaService {

    Optional<BodaDTO> findById(Long id);

    List<BodaDTO> findAll();

    BodaDTO save(BodaDTO dto);

    void deleteById (Long id);

    BodaDTO toDTO(Boda boda);

}
