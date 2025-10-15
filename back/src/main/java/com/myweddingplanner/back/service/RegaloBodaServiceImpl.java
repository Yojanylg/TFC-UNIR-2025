package com.myweddingplanner.back.service;

import com.myweddingplanner.back.model.RegaloBoda;
import com.myweddingplanner.back.repository.RegaloBodaRepository;

import java.util.List;
import java.util.Optional;

public class RegaloBodaServiceImpl implements RegaloBodaService {

    private final RegaloBodaRepository repository;

    public RegaloBodaServiceImpl(RegaloBodaRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<RegaloBoda> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public List<RegaloBoda> findAll() {
        return repository.findAll();
    }

    @Override
    public RegaloBoda save(RegaloBoda regaloBoda) {
        return repository.save(regaloBoda);
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
