package com.myweddingplanner.back.service;

import com.myweddingplanner.back.model.Alergeno;
import com.myweddingplanner.back.model.Boda;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface BodaService {

    Optional<Boda> findById(Long id);

    List<Boda> findAll();

    Boda save(Boda boda);

    void deleteById (Long id);
}
