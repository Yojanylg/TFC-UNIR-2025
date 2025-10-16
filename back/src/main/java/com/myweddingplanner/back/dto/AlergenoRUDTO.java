package com.myweddingplanner.back.dto;

import com.myweddingplanner.back.model.ImagenAlergeno;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AlergenoRUDTO {

    private Long id;
    private String nombre;
    private List<ImagenAlergeno> imagenes;
}
