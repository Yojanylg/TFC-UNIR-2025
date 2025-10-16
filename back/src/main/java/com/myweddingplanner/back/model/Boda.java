package com.myweddingplanner.back.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "bodas")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString(exclude = {"itinerario", "regalosBoda", "invitados", "novios"})
public class Boda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    private String lugar;

    private String fecha;

    // RELACION CON ITINERARIO
    @OneToOne(cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "id_itinerario")
    private Itinerario itinerario;

    // RELACION CON REGALO BODA
    @OneToMany(mappedBy = "boda", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RegaloBoda> regalosBoda;

    // RELACION CON USUARIOS POR MEDIO DE INVITACION-USUARIO
    @OneToMany(mappedBy = "boda", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<InvitacionUsuario> invitados;

    // RELACION CON USUARIOS POR MEDIO DE BODAS-USUARIO
    @OneToMany(mappedBy = "boda", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<BodaUsuario> novios;





}