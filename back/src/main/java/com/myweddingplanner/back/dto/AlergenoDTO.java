package com.myweddingplanner.back.dto;

import com.myweddingplanner.back.model.ImagenAlergeno;
import com.myweddingplanner.back.model.UsuarioAlergeno;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AlergenoDTO {

    private Long id;

    private String nombre;

}
