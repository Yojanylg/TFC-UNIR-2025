package com.myweddingplanner.back.service;

import com.myweddingplanner.back.model.BodaUsuario;
import com.myweddingplanner.back.repository.BodaUsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BodaUsuarioServiceImpl implements BodaUsuarioService{

    private final BodaUsuarioRepository repository;

    public BodaUsuarioServiceImpl(BodaUsuarioRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<BodaUsuario> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public List<BodaUsuario> findAll() {
        return List.of();
    }

    @Override
    public BodaUsuario save(BodaUsuario bodaUsuario) {
        return null;
    }

    @Override
    public void deleteById(Long id) {

    }
}
