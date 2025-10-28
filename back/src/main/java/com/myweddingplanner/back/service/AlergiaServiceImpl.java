package com.myweddingplanner.back.service;

import com.myweddingplanner.back.model.UsuarioAlergeno;
import com.myweddingplanner.back.repository.UsuarioAlergenoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AlergiaServiceImpl implements AlergiaService {

    private final UsuarioAlergenoRepository usuarioAlergenoRepository;

    public AlergiaServiceImpl(UsuarioAlergenoRepository usuarioAlergenoRepository) {
        this.usuarioAlergenoRepository = usuarioAlergenoRepository;
    }

    @Override
    public Optional<UsuarioAlergeno> findById(Long id) {
        return usuarioAlergenoRepository.findById(id);
    }

    @Override
    public List<UsuarioAlergeno> findAll() {
        return usuarioAlergenoRepository.findAll();
    }

    @Override
    public UsuarioAlergeno save(UsuarioAlergeno usuarioAlergeno) {
        return usuarioAlergenoRepository.save(usuarioAlergeno);
    }

    @Override
    public void deleteById(Long id) {
        usuarioAlergenoRepository.deleteById(id);
    }

}
