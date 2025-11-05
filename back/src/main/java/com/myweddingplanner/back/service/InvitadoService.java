package com.myweddingplanner.back.service;

import com.myweddingplanner.back.dto.InvitadoDTO;
import com.myweddingplanner.back.model.Invitacion;


import java.util.List;
import java.util.Optional;

public interface InvitadoService {

    Optional<Invitacion> findById(Long id);

    List<Invitacion> findByUsuario(Long usuario);

    List<Invitacion> findAll();

    Invitacion save(Invitacion invitacion);

    void deleteById (Long id);

    InvitadoDTO toDTO (Invitacion invitacion);
}
