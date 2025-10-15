package com.myweddingplanner.back.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "bodas_usuario")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BodaUsuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_boda")
    private Boda boda;

    // Relacion con Usuario
    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;
}
