package com.myweddingplanner.back.mappers;

import com.myweddingplanner.back.dto.ImagenProductoDTO;
import com.myweddingplanner.back.model.ImagenProducto;
import com.myweddingplanner.back.model.Producto;

public class ImagenProductoMapper {

    private ImagenProductoMapper(){}

    public static ImagenProductoDTO toDTO(ImagenProducto entity) {
        if (entity == null) return null;
        ImagenProductoDTO dto = new ImagenProductoDTO();
        dto.setId(entity.getId());
        dto.setEnlace(entity.getEnlace());
        dto.setTipo(entity.getTipo());
        dto.setProductoId(entity.getProducto() != null ? entity.getProducto().getId() : null);
        return dto;
    }

    /** Crea entidad NUEVA desde DTO (no setea id). */
    public static ImagenProducto toNewEntity(ImagenProductoDTO dto, Producto owner) {
        if (dto == null) return null;
        ImagenProducto img = new ImagenProducto();
        img.setEnlace(dto.getEnlace());
        img.setTipo(dto.getTipo());
        img.setProducto(owner);
        return img;
    }

    /** Actualiza entidad existente con datos del DTO (PUT: reemplazo completo). */
    public static void updateEntity(ImagenProducto entity, ImagenProductoDTO dto) {
        if (dto.getEnlace() != null) entity.setEnlace(dto.getEnlace());
        if (dto.getTipo() != null) entity.setTipo(dto.getTipo());
        // producto lo gestiona el padre
    }
}
