package com.myweddingplanner.back.service;

import com.myweddingplanner.back.model.Producto;
import com.myweddingplanner.back.repository.ProductoRepository;

import java.util.List;
import java.util.Optional;

public class ProductoServiceImpl implements ProductoService{

    private final ProductoRepository repository;

    public ProductoServiceImpl(ProductoRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<Producto> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public List<Producto> findAll() {
        return repository.findAll();
    }

    @Override
    public Producto save(Producto producto) {
        return repository.save(producto);
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
