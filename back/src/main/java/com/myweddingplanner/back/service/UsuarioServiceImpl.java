package com.myweddingplanner.back.service;

import com.myweddingplanner.back.dto.UsuarioDTO;
import com.myweddingplanner.back.mappers.UsuarioMapper;
import com.myweddingplanner.back.model.Usuario;
import com.myweddingplanner.back.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UsuarioServiceImpl implements UsuarioService{

    private final UsuarioRepository repository;


    public UsuarioServiceImpl(UsuarioRepository repository) {
        this.repository = repository;

    }

    @Override
    public Optional<UsuarioDTO> findById(Long id) {

        return repository.findById(id).map(UsuarioMapper::toDTO);
    }

    @Override
    public List<UsuarioDTO> findAll() {

        return repository.findAll()
                .stream()
                .map(UsuarioMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public UsuarioDTO save(UsuarioDTO dto) {
        Usuario usuario = UsuarioMapper.toEntity(dto);
        Usuario salvado = repository.save(usuario);

        return UsuarioMapper.toDTO(salvado);
    }

    @Override
    public void deleteById(Long id) {

        repository.deleteById(id);
    }

}
