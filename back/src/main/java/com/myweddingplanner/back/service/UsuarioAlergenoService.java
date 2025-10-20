package com.myweddingplanner.back.service;

import com.myweddingplanner.back.dto.UsuarioAlergenoDTO;
import com.myweddingplanner.back.dto.UsuarioDTO;
import com.myweddingplanner.back.model.Boda;
import com.myweddingplanner.back.model.Usuario;
import com.myweddingplanner.back.model.UsuarioAlergeno;

import java.util.List;
import java.util.Optional;

public interface UsuarioAlergenoService {

    Optional<UsuarioAlergeno> findById(Long id);

    List<UsuarioAlergeno> findAll();

    UsuarioAlergeno save(UsuarioAlergeno usuarioAlergeno);

    void deleteById (Long id);

}
