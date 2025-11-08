package com.myweddingplanner.back.service;

import com.myweddingplanner.back.dto.WeddingDTO;
import com.myweddingplanner.back.model.Wedding;

import java.util.List;
import java.util.Optional;

public interface WeddingService {

    Optional<WeddingDTO> findById(Long id);

    List<WeddingDTO> findAll();

    Wedding save(Wedding wedding);

    void deleteById (Long id);

}
