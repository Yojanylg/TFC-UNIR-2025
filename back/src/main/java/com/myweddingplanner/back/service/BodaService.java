package com.myweddingplanner.back.service;

import com.myweddingplanner.back.dto.BodaDTO;
import com.myweddingplanner.back.dto.MisBodaDTO;
import com.myweddingplanner.back.model.Boda;

import java.util.List;
import java.util.Optional;

public interface BodaService {

    Optional<BodaDTO> findById(Long id);

    List<BodaDTO> findAll();

    BodaDTO save(BodaDTO dto);

    void deleteById (Long id);

    List<BodaDTO> buscarBodasPorUsuario(Long usuario);

    Optional<BodaDTO> buscarBodaActualPorUsuario(Long usuario);

    BodaDTO toDTO(Boda boda);

    MisBodaDTO toMisBodaDTO(Boda boda);

}
