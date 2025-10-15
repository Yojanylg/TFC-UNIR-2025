package com.myweddingplanner.back.service;

import com.myweddingplanner.back.model.InvitacionUsuario;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface InvitacionUsuarioService {

    Optional<InvitacionUsuario> findById(Long id);

    List<InvitacionUsuario> findAll();

    InvitacionUsuario save(InvitacionUsuario usuarioInvitado);

    void deleteById (Long id);
}
