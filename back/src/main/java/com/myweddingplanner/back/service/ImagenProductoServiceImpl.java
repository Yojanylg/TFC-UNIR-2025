package com.myweddingplanner.back.service;

import com.myweddingplanner.back.dto.ImagenProductoDTO;
import com.myweddingplanner.back.model.ImagenProducto;
import com.myweddingplanner.back.repository.ImagenProductoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ImagenProductoServiceImpl implements ImagenProductoService {

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

    @Override
    public ImagenProductoDTO toDTO(ImagenProducto imagenProducto) {
        ImagenProductoDTO dto = new ImagenProductoDTO();
        dto.setId(imagenProducto.getId());
        dto.setEnlace(imagenProducto.getEnlace());
        dto.setTipo(imagenProducto.getTipo());
        return dto;
    }
}
