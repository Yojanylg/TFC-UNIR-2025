package com.myweddingplanner.back.service;

import com.myweddingplanner.back.model.Regalo;
import com.myweddingplanner.back.repository.RegaloRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RegaloServiceImpl implements RegaloService{

    private final RegaloRepository repository;

    public RegaloServiceImpl(RegaloRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<Regalo> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public List<Regalo> findAll() {
        return repository.findAll();
    }

    @Override
    public Regalo save(Regalo regalo) {
        return repository.save(regalo);
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
