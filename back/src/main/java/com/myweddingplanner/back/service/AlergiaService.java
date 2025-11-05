package com.myweddingplanner.back.service;

import com.myweddingplanner.back.model.Alergia;

import java.util.List;
import java.util.Optional;

public interface AlergiaService {

    Optional<Alergia> findById(Long id);

    List<Alergia> findAll();

    Alergia save(Alergia alergia);

    void deleteById (Long id);

}
