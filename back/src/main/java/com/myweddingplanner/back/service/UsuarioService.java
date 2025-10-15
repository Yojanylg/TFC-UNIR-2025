package com.myweddingplanner.back.service;

import com.myweddingplanner.back.model.Usuario;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface UsuarioService {

    Optional<Usuario> findById(Long id);

    List<Usuario> findAll();

    Usuario save(Usuario usuario);

    void deleteById (Long id);
}
