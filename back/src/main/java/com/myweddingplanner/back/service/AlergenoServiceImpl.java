package com.myweddingplanner.back.service;

import com.myweddingplanner.back.model.Alergeno;
import com.myweddingplanner.back.repository.AlergenoRepository;

import java.util.List;
import java.util.Optional;

public class AlergenoServiceImpl implements AlergenoService{

    private final AlergenoRepository repository;

    public AlergenoServiceImpl(AlergenoRepository repository) {
        this.repository = repository;
    }


    @Override
    public Optional<Alergeno> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public List<Alergeno> findAll() {
        return repository.findAll();
    }

    @Override
    public Alergeno save(Alergeno alergeno) {
        return repository.save(alergeno);
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
