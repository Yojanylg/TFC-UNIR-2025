package com.myweddingplanner.back.repository;

import com.myweddingplanner.back.model.Invitacion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InvitadoRepository extends JpaRepository<Invitacion, Long> {

    List<Invitacion> findByUsuario(Long usuario);
}
