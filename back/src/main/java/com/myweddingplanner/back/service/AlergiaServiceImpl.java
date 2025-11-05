package com.myweddingplanner.back.service;

import com.myweddingplanner.back.model.Alergia;
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
    public Optional<Alergia> findById(Long id) {
        return usuarioAlergenoRepository.findById(id);
    }

    @Override
    public List<Alergia> findAll() {
        return usuarioAlergenoRepository.findAll();
    }

    @Override
    public Alergia save(Alergia alergia) {
        return usuarioAlergenoRepository.save(alergia);
    }

    @Override
    public void deleteById(Long id) {
        usuarioAlergenoRepository.deleteById(id);
    }

}
