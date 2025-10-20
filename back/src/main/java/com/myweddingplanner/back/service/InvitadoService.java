package com.myweddingplanner.back.service;

import com.myweddingplanner.back.model.Invitado;


import java.util.List;
import java.util.Optional;

public interface InvitadoService {

    Optional<Invitado> findById(Long id);

    List<Invitado> findAll();

    Invitado save(Invitado invitado);

    void deleteById (Long id);
}
