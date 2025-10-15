package com.myweddingplanner.back.service;

import com.myweddingplanner.back.model.Alergeno;
import com.myweddingplanner.back.model.ImagenAlergeno;
import com.myweddingplanner.back.model.ImagenProducto;
import com.myweddingplanner.back.repository.ImagenAlergenoRepository;

import java.util.List;
import java.util.Optional;

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
}
