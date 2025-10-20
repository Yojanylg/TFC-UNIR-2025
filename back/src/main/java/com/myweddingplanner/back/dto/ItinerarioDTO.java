package com.myweddingplanner.back.dto;

import com.myweddingplanner.back.model.Boda;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ItinerarioDTO {

    private Long id;
    private String descripcion;
}
