package com.myweddingplanner.back.mappers;

import com.myweddingplanner.back.dto.ImagenProductoDTO;
import com.myweddingplanner.back.dto.ProductoDTO;
import com.myweddingplanner.back.model.ImagenProducto;
import com.myweddingplanner.back.model.Producto;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class ProductoMapper {

    private ProductoMapper() {}

    public static ProductoDTO toDTO(Producto entity) {
        if (entity == null) return null;
        ProductoDTO dto = new ProductoDTO();
        dto.setId(entity.getId());
        dto.setNombre(entity.getNombre());
        dto.setDescripcion(entity.getDescripcion());
        dto.setEnlaceCompra(entity.getEnlaceCompra());
        dto.setValor(entity.getValor());

        if (entity.getImagenes() != null) {
            dto.setImagenes(
                    entity.getImagenes().stream()
                            .map(ImagenProductoMapper::toDTO)
                            .collect(Collectors.toList())
            );
        }
        return dto;
    }

    /** Crea entidad NUEVA desde DTO (para POST). */
    public static Producto toNewEntity(ProductoDTO dto) {
        if (dto == null) return null;
        Producto p = new Producto();
        p.setNombre(dto.getNombre());
        p.setDescripcion(dto.getDescripcion());
        p.setEnlaceCompra(dto.getEnlaceCompra());
        p.setValor(dto.getValor() != null ? dto.getValor() : 0.0);

        if (dto.getImagenes() != null) {
            List<ImagenProducto> imagenes = new ArrayList<>();
            for (ImagenProductoDTO imgDTO : dto.getImagenes()) {
                ImagenProducto img = ImagenProductoMapper.toNewEntity(imgDTO, p);
                imagenes.add(img);
            }
            p.setImagenes(imagenes);
        } else {
            p.setImagenes(new ArrayList<>());
        }
        return p;
    }

    /** PUT (reemplazo): actualiza campos escalares y sincroniza completamente la lista de imágenes. */
    public static void putEntity(Producto entity, ProductoDTO dto) {
        entity.setNombre(dto.getNombre());
        entity.setDescripcion(dto.getDescripcion());
        entity.setEnlaceCompra(dto.getEnlaceCompra());
        entity.setValor(dto.getValor() != null ? dto.getValor() : 0.0);

        // Sincronización de imágenes por id:
        Map<Long, ImagenProducto> actualesPorId = Optional.ofNullable(entity.getImagenes())
                .orElseGet(ArrayList::new)
                .stream()
                .filter(i -> i.getId() != null)
                .collect(Collectors.toMap(ImagenProducto::getId, i -> i));

        List<ImagenProducto> nuevas = new ArrayList<>();
        if (dto.getImagenes() != null) {
            for (ImagenProductoDTO imgDTO : dto.getImagenes()) {
                if (imgDTO.getId() != null && actualesPorId.containsKey(imgDTO.getId())) {
                    // update existente
                    ImagenProducto existente = actualesPorId.get(imgDTO.getId());
                    ImagenProductoMapper.updateEntity(existente, imgDTO);
                    existente.setProducto(entity);
                    nuevas.add(existente);
                } else {
                    // create nueva
                    ImagenProducto nueva = ImagenProductoMapper.toNewEntity(imgDTO, entity);
                    nuevas.add(nueva);
                }
            }
        }
        // Reemplazo de colección (requiere orphanRemoval=true para borrar huérfanas automáticamente)
        entity.getImagenes().clear();
        entity.getImagenes().addAll(nuevas);
    }

    /** PATCH (parcial): sólo actualiza campos no nulos; imágenes si vienen, se sincronizan. */
    public static void patchEntity(Producto entity, ProductoDTO dto) {
        if (dto.getNombre() != null) entity.setNombre(dto.getNombre());
        if (dto.getDescripcion() != null) entity.setDescripcion(dto.getDescripcion());
        if (dto.getEnlaceCompra() != null) entity.setEnlaceCompra(dto.getEnlaceCompra());
        if (dto.getValor() != null) entity.setValor(dto.getValor());

        if (dto.getImagenes() != null) {
            putEntity(entity, dto); // Reutilizamos misma lógica de sincronización de imágenes
        }
    }
}
