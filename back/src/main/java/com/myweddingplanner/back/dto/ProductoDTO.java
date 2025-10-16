package com.myweddingplanner.back.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductoDTO {

    private Long id;

    private String nombre;

    private String descripcion;

    private String enlaceCompra;

    @NotNull
    @PositiveOrZero
    private Double valor;

    private List<ImagenProductoDTO> imagenes = new ArrayList<>();


}
