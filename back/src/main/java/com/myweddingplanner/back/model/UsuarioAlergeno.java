package com.myweddingplanner.back.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "alergias")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString(exclude = {"usuario"})
public class UsuarioAlergeno {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @Column(name = "id_alergeno")
    private Long idAlergeno;

    private String nombre;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

}
