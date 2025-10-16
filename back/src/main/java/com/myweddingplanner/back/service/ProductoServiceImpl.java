package com.myweddingplanner.back.service;

import com.myweddingplanner.back.dto.AlergenoDTO;
import com.myweddingplanner.back.dto.ImagenAlergenoDTO;
import com.myweddingplanner.back.dto.ImagenProductoDTO;
import com.myweddingplanner.back.dto.ProductoDTO;
import com.myweddingplanner.back.model.Alergeno;
import com.myweddingplanner.back.model.ImagenAlergeno;
import com.myweddingplanner.back.model.ImagenProducto;
import com.myweddingplanner.back.model.Producto;
import com.myweddingplanner.back.repository.ProductoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductoServiceImpl implements ProductoService{


    private final ProductoRepository repository;

    public ProductoServiceImpl(ProductoRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ProductoDTO> findById(Long id) {

        Optional<Producto> opt = repository.findById(id);

        return (opt.isEmpty()) ? Optional.empty() : Optional.of(toDTO(opt.get()));
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProductoDTO> findAll() {

        List<ProductoDTO> list = new ArrayList<>();

        for (Producto a: repository.findAll()){
            list.add(toDTO(a));
        }
        return list;
    }

    @Override
    public ProductoDTO save(ProductoDTO dto) {
        Producto entity = (dto.getId() != null)
                ? repository.findById(dto.getId()).orElseGet(Producto::new)
                : new Producto();

        entity.setNombre(dto.getNombre());
        entity.setDescripcion(dto.getDescripcion());
        entity.setEnlaceCompra(dto.getEnlaceCompra());
        entity.setValor(dto.getValor());

        List<ImagenProducto> imgs = (dto.getImagenes() == null) ? List.of()
                : dto.getImagenes().stream().map(this::toEntityImagen).toList();

        entity.setImagenes(imgs);
        Producto saved = repository.save(entity);
        return toDTO(saved);
    }

    @Override
    public void deleteById(Long id) {

        repository.deleteById(id);

    }

    @Override
    public ProductoDTO toDTO(Producto producto) {
        ProductoDTO dto = new ProductoDTO();

        dto.setId(producto.getId());
        dto.setNombre(producto.getNombre());
        dto.setDescripcion(producto.getDescripcion());
        dto.setEnlaceCompra(producto.getEnlaceCompra());
        dto.setValor(producto.getValor());

        List<ImagenProductoDTO> imagenes = (producto.getImagenes() == null) ?
                List.of() : producto.getImagenes().stream().map(this::toDTOImagen).toList();
        dto.setImagenes(new ArrayList<>(imagenes));

        return dto;
    }

    @Override
    public Producto toEntity(ProductoDTO dto) {

        Producto p = new Producto();

        if (dto.getId() != null) p.setId(dto.getId());

        p.setNombre(dto.getNombre());
        p.setDescripcion(dto.getDescripcion());
        p.setEnlaceCompra(dto.getEnlaceCompra());
        p.setValor(dto.getValor());

        if (dto.getImagenes() != null) {
            List<ImagenProducto> imagenes = new ArrayList<>();

            for (ImagenProductoDTO imgDto : dto.getImagenes()){
                ImagenProducto img = new ImagenProducto();
                img.setId(imgDto.getId());
                img.setEnlace(imgDto.getEnlace());
                img.setTipo(imgDto.getTipo());
                img.setProducto(p);
                imagenes.add(img);
            }
            p.setImagenes(imagenes);
        }
        return p;
    }

    private ImagenProductoDTO toDTOImagen(ImagenProducto img) {
        ImagenProductoDTO dto = new ImagenProductoDTO();
        dto.setId(img.getId());
        dto.setEnlace(img.getEnlace());
        dto.setTipo(img.getTipo());
        return dto;
    }

    private ImagenProducto toEntityImagen(ImagenProductoDTO dto) {
        ImagenProducto e = new ImagenProducto();
        e.setId(dto.getId());
        e.setEnlace(dto.getEnlace());
        e.setTipo(dto.getTipo());
        return e;
    }

}
