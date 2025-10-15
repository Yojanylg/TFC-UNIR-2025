package com.myweddingplanner.back.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "alergenos")
public class Alergeno {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    @OneToMany(mappedBy = "alergeno")
    private List<ImagenAlergeno> imagenes;

    @OneToMany(mappedBy = "alergeno")
    private List<UsuarioAlergeno> usuarios;

}