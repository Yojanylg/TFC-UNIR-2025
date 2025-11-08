package com.myweddingplanner.back.service;

import com.myweddingplanner.back.dto.AllergenDTO;
import com.myweddingplanner.back.model.Allergen;
import java.util.List;
import java.util.Optional;

public interface AllergenService {

    Optional<Allergen> findById(Long id);

    List<AllergenDTO> findAll();

    Allergen save(Allergen allergen);

    void deleteById(Long id);

}
