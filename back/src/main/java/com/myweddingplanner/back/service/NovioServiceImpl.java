package com.myweddingplanner.back.service;

import com.myweddingplanner.back.model.Novio;
import com.myweddingplanner.back.repository.NovioRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NovioServiceImpl implements NovioService{

    private final NovioRepository repository;

    public NovioServiceImpl(NovioRepository repository) {
        this.repository = repository;
    }


    @Override
    public Optional<Novio> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public Optional<Novio> findByEmail(String email) {
        return repository.findByEmail(email);
    }


    @Override
    public boolean existsByEmail(String email) {
        return repository.existsByEmail(email);
    }

    @Override
    public List<Novio> findAll() {
        return repository.findAll();
    }

    @Override
    public Novio save(Novio novio) {
        return repository.save(novio);
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }


}
