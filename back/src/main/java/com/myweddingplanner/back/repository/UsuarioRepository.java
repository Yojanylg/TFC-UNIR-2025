package com.myweddingplanner.back.repository;

import com.myweddingplanner.back.model.Usuario;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    @EntityGraph(attributePaths = "rol")
    Optional<Usuario> findByEmail(String email);

    boolean existsByEmail(String email);

    @EntityGraph(attributePaths = "rol")
    Optional<Usuario> findWithRolByEmail(String email);
}
