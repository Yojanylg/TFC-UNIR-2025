package com.myweddingplanner.back.service;

import com.myweddingplanner.back.model.Invitado;
import com.myweddingplanner.back.repository.InvitadoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InivtadoServiceImpl implements InvitadoService{

    private final InvitadoRepository repository;

    public InivtadoServiceImpl(InvitadoRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<Invitado> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public List<Invitado> findAll() {
        return repository.findAll();
    }

    @Override
    public Invitado save(Invitado invitado) {
        return repository.save(invitado);
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
