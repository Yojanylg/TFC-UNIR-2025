package com.myweddingplanner.back.service;

import com.myweddingplanner.back.dto.UsuarioDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public interface UsuarioService {

    Optional<UsuarioDTO> findById(Long id);

    List<UsuarioDTO> findAll();

    UsuarioDTO save(UsuarioDTO dto);

    void deleteById (Long id);
}
