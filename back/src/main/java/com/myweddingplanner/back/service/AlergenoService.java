package com.myweddingplanner.back.service;

import com.myweddingplanner.back.model.Alergeno;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface AlergenoService {

    Optional<Alergeno> findById(Long id);

    List<Alergeno> findAll();

    Alergeno save(Alergeno alergeno);

    void deleteById (Long id);
}
