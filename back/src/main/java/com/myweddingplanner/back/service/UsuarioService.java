package com.myweddingplanner.back.service;

import com.myweddingplanner.back.dto.UsuarioDTO;
import com.myweddingplanner.back.model.Usuario;

import java.util.List;
import java.util.Optional;

public interface UsuarioService {

    Optional<UsuarioDTO> findById(Long id);

    List<UsuarioDTO> findAll();

    UsuarioDTO save(UsuarioDTO dto);

    void deleteById (Long id);

    UsuarioDTO toDTO(Usuario a);

    Usuario toEntity(UsuarioDTO dto);
}
