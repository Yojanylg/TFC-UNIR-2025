package com.myweddingplanner.back.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class InvitacionUsuario {

    // TODO
    // MODIFICAR USUARIO INTIDADO A INVITACION USUARIO

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "acompanantes_menores")
    private int acomptesMenores;
    @Column(name = "acompanantes_mayores")
    private int acomptesMayores;
    private boolean confirmado;

    @ManyToOne
    @JoinColumn(name = "id_boda")
    private Boda boda;

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;
}