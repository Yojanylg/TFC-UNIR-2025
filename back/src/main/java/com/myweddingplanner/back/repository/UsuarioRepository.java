package com.myweddingplanner.back.repository;

import com.myweddingplanner.back.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

// TODO findByEmail
    Optional<Usuario> findByEmail(String email);
    boolean existsByEmail(String email);
}
