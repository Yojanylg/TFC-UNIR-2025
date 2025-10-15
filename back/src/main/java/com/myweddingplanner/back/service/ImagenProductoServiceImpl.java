package com.myweddingplanner.back.service;

import com.myweddingplanner.back.model.ImagenProducto;
import com.myweddingplanner.back.repository.ImagenProductoRepository;

import java.util.List;
import java.util.Optional;

public class ImagenProductoServiceImpl implements ImagenProductoServide{

    private final ImagenProductoRepository repository;

    public ImagenProductoServiceImpl(ImagenProductoRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<ImagenProducto> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public List<ImagenProducto> findAll() {
        return repository.findAll();
    }

    @Override
    public ImagenProducto save(ImagenProducto imagen) {
        return repository.save(imagen);
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
