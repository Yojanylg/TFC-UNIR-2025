package com.myweddingplanner.back.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductoDTO {

    private Long id;

    private String nombre;

    private String descripcion;

    private String enlaceCompra;

    private double valor;

   private List<ImagenProductoDTO> imagenes = new ArrayList<>();

}
