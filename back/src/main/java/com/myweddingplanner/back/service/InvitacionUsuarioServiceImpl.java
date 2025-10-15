package com.myweddingplanner.back.service;


import com.myweddingplanner.back.model.InvitacionUsuario;
import com.myweddingplanner.back.repository.InvitacionUsuarioRepository;

import java.util.List;
import java.util.Optional;

public class InvitacionUsuarioServiceImpl implements InvitacionUsuarioService {

    private final InvitacionUsuarioRepository repository;

    public InvitacionUsuarioServiceImpl(InvitacionUsuarioRepository repository) {
        this.repository = repository;
    }


    @Override
    public Optional<InvitacionUsuario> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public List<InvitacionUsuario> findAll() {
        return repository.findAll();
    }

    @Override
    public InvitacionUsuario save(InvitacionUsuario usuarioInvitado) {
        return repository.save(usuarioInvitado);
    }

    @Override
    public void deleteById(Long id) {

        repository.deleteById(id);
    }
}

