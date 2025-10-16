package com.myweddingplanner.back.dto;

import com.myweddingplanner.back.model.Alergeno;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ImagenAlergenoRUDTO {

    private Long id;
    private String enlace;
    private String tipo;
    private Alergeno alergeno;
}
