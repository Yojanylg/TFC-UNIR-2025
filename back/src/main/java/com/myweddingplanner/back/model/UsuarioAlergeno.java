package com.myweddingplanner.back.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "usuarios_alergenos")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UsuarioAlergeno {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "id_alergeno")
    private Alergeno alergeno;
}
