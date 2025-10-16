package com.myweddingplanner.back.service;

import com.myweddingplanner.back.dto.AlergenoDTO;
import com.myweddingplanner.back.dto.AlergenoRUDTO;
import com.myweddingplanner.back.mappers.AlergenoMapper;
import com.myweddingplanner.back.model.Alergeno;
import com.myweddingplanner.back.model.ImagenAlergeno;
import com.myweddingplanner.back.repository.AlergenoRepository;
import com.myweddingplanner.back.repository.ImagenAlergenoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AlergenoServiceImpl implements AlergenoService{

    private final AlergenoRepository repository;
    private final ImagenAlergenoRepository imagenAlergenoRepository;

    public AlergenoServiceImpl(AlergenoRepository repository, ImagenAlergenoRepository imagenAlergenoRepository) {
        this.repository = repository;
        this.imagenAlergenoRepository = imagenAlergenoRepository;
    }


    @Override
    public Optional<Alergeno> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public List<Alergeno> findAll() {

        return repository.findAll();
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


    @Override
    public AlergenoRUDTO toRUDTO(Alergeno alergeno) {

        AlergenoRUDTO dto = new AlergenoRUDTO();

        dto.setId(alergeno.getId());
        dto.setNombre(alergeno.getNombre());
        dto.setImagenes(alergeno.getImagenes());

        return dto;
    }
}
