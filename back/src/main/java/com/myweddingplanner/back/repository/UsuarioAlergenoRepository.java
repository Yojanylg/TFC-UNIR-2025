package com.myweddingplanner.back.repository;

import com.myweddingplanner.back.model.Alergia;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioAlergenoRepository extends JpaRepository<Alergia, Long> {
}
