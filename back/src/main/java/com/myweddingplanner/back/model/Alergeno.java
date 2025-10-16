package com.myweddingplanner.back.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "alergenos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString(exclude = {})
public class Alergeno {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    private String nombre;

    // bidirecional
    //@OneToMany(mappedBy = "alergeno")
    //private List<ImagenAlergeno> imagenes;

    // unidireccional
    @OneToMany
    @JoinColumn(name = "id_alergeno")
    private List<ImagenAlergeno> imagenes;

    @OneToMany(mappedBy = "alergeno")
    private List<UsuarioAlergeno> usuarios;

}