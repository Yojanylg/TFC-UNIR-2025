package com.myweddingplanner.back.service;

import com.myweddingplanner.back.dto.ImagenAlergenoDTO;
import com.myweddingplanner.back.model.ImagenAlergeno;
import com.myweddingplanner.back.repository.ImagenAlergenoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ImagenAlergenoServiceImpl implements ImagenAlergenoService{

    private final ImagenAlergenoRepository repository;

    public ImagenAlergenoServiceImpl(ImagenAlergenoRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<ImagenAlergeno> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public List<ImagenAlergeno> findAll() {
        return repository.findAll();
    }

    @Override
    public ImagenAlergeno save(ImagenAlergeno imagen) {
        return repository.save(imagen);
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    @Override
    public ImagenAlergenoDTO toDTO(ImagenAlergeno imagenAlergeno) {
        ImagenAlergenoDTO dto = new ImagenAlergenoDTO();
        dto.setId(imagenAlergeno.getId());
        dto.setEnlace(imagenAlergeno.getEnlace());
        dto.setTipo(imagenAlergeno.getTipo());
        return dto;
    }
}
