package com.myweddingplanner.back.service;

import com.myweddingplanner.back.model.Novio;

import java.util.List;
import java.util.Optional;

public interface NovioService {

    Optional<Novio> findById(Long id);

    List<Novio> findAll();

    Novio save(Novio novio);

    void deleteById (Long id);
}
