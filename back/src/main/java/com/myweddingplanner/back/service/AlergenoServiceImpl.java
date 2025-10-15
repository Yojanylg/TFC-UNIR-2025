package com.myweddingplanner.back.service;

import com.myweddingplanner.back.dto.AlergenoDTO;
import com.myweddingplanner.back.mappers.AlergenoMapper;
import com.myweddingplanner.back.model.Alergeno;
import com.myweddingplanner.back.repository.AlergenoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AlergenoServiceImpl implements AlergenoService{

    private final AlergenoRepository repository;

    public AlergenoServiceImpl(AlergenoRepository repository) {
        this.repository = repository;
    }


    @Override
    public Optional<AlergenoDTO> findById(Long id) {
        return repository.findById(id).map(AlergenoMapper::toDTO);
    }

    @Override
    public List<AlergenoDTO> findAll() {
        return repository.findAll()
                .stream()
                .map(AlergenoMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public AlergenoDTO save(AlergenoDTO dto) {
        Alergeno alergeno = AlergenoMapper.toEntity(dto);
        Alergeno salvado = repository.save(alergeno);

        return AlergenoMapper.toDTO(salvado);
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
