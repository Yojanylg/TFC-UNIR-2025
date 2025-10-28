package com.myweddingplanner.back.service;

import com.myweddingplanner.back.model.UsuarioAlergeno;

import java.util.List;
import java.util.Optional;

public interface AlergiaService {

    Optional<UsuarioAlergeno> findById(Long id);

    List<UsuarioAlergeno> findAll();

    UsuarioAlergeno save(UsuarioAlergeno usuarioAlergeno);

    void deleteById (Long id);

}
