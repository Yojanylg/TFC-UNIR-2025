package com.myweddingplanner.back.service;

import com.myweddingplanner.back.model.Usuario;
import com.myweddingplanner.back.repository.UsuarioRepository;

import java.util.List;
import java.util.Optional;

public class UsuarioServiceImpl implements UsuarioService{

    private final UsuarioRepository repository;

    public UsuarioServiceImpl(UsuarioRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<Usuario> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public List<Usuario> findAll() {
        return repository.findAll();
    }

    @Override
    public Usuario save(Usuario usuario) {
        return repository.save(usuario);
    }

    @Override
    public void deleteById(Long id) {

        repository.deleteById(id);
    }
}
