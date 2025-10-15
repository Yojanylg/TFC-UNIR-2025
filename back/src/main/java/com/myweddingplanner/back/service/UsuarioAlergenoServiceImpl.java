package com.myweddingplanner.back.service;

import com.myweddingplanner.back.model.UsuarioAlergeno;
import com.myweddingplanner.back.repository.UsuarioAlergenoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioAlergenoServiceImpl implements UsuarioAlergenoService{

    private final UsuarioAlergenoRepository usuarioAlergenoRepository;

    public UsuarioAlergenoServiceImpl(UsuarioAlergenoRepository usuarioAlergenoRepository) {
        this.usuarioAlergenoRepository = usuarioAlergenoRepository;
    }

    @Override
    public Optional<UsuarioAlergeno> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public List<UsuarioAlergeno> findAll() {
        return List.of();
    }

    @Override
    public UsuarioAlergeno save(UsuarioAlergeno usuarioAlergeno) {
        return null;
    }

    @Override
    public void deleteById(Long id) {

    }
}
