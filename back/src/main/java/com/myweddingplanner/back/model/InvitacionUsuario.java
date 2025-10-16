package com.myweddingplanner.back.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "invitacion_usuarios")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString(exclude = {"boda", "usuario"})
public class InvitacionUsuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @Column(name = "acompanantes_menores")
    private int acomptesMenores;
    @Column(name = "acompanantes_mayores")
    private int acomptesMayores;
    private boolean confirmado;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_boda")
    private Boda boda;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;
}