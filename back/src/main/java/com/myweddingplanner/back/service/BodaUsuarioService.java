package com.myweddingplanner.back.service;

import com.myweddingplanner.back.model.BodaUsuario;

import java.util.List;
import java.util.Optional;

public interface BodaUsuarioService {

    Optional<BodaUsuario> findById(Long id);

    List<BodaUsuario> findAll();

    BodaUsuario save(BodaUsuario bodaUsuario);

    void deleteById (Long id);
}
