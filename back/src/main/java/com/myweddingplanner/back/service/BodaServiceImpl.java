package com.myweddingplanner.back.service;

import com.myweddingplanner.back.model.Alergeno;
import com.myweddingplanner.back.model.Boda;
import com.myweddingplanner.back.repository.BodaRepository;

import java.util.List;
import java.util.Optional;

public class BodaServiceImpl implements BodaService{

    private final BodaRepository repository;

    public BodaServiceImpl(BodaRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<Boda> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public List<Boda> findAll() {
        return repository.findAll();
    }

    @Override
    public Boda save(Boda boda) {
        return repository.save(boda);
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
