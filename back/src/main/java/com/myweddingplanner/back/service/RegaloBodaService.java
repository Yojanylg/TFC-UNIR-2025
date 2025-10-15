package com.myweddingplanner.back.service;

import com.myweddingplanner.back.model.RegaloBoda;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface RegaloBodaService {

    Optional<RegaloBoda> findById(Long id);

    List<RegaloBoda> findAll();

    RegaloBoda save(RegaloBoda regaloBoda);

    void deleteById (Long id);
}
