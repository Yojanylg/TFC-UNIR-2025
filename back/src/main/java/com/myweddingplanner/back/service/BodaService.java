package com.myweddingplanner.back.service;

import com.myweddingplanner.back.model.Boda;

import java.util.List;
import java.util.Optional;

public interface BodaService {

    Optional<Boda> findById(Long id);

    List<Boda> findAll();

    Boda save(Boda dto);

    void deleteById (Long id);
}
