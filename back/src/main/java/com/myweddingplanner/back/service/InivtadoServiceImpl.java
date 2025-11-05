package com.myweddingplanner.back.service;

import com.myweddingplanner.back.dto.InvitadoDTO;
import com.myweddingplanner.back.model.Invitacion;
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
    public Optional<Invitacion> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public List<Invitacion> findByUsuario(Long usuario) {
        return repository.findByUsuario(usuario);
    }

    @Override
    public List<Invitacion> findAll() {
        return repository.findAll();
    }

    @Override
    public Invitacion save(Invitacion invitacion) {
        return repository.save(invitacion);
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    @Override
    public InvitadoDTO toDTO(Invitacion invitacion) {

        InvitadoDTO dto = new InvitadoDTO();

        dto.setId(invitacion.getId());
        dto.setIdUsuario(invitacion.getUsuario());
        dto.setNombre(invitacion.getNombre());
        dto.setApellido1(invitacion.getApellido1());
        dto.setApellido2(invitacion.getApellido2());
        dto.setEmail(invitacion.getEmail());
        dto.setConfirmado(invitacion.isConfirmado());
        dto.setAcomptesMenores(invitacion.getAcomptesMenores());
        dto.setAcomptesMayores(invitacion.getAcomptesMayores());

        return dto;
    }
}
