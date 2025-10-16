package com.myweddingplanner.back.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ImagenProductoDTO {

    private Long id;

    @NotBlank
    private String enlace;

    private String tipo;

    private Long productoId;
}
