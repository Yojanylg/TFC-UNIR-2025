package com.myweddingplanner.back.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "bodas")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Boda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String lugar;

    private String fecha;

    // RELACION CON ITINERARIO
    @OneToOne(mappedBy = "boda")
    private Itinerario itinerario;

    // RELACION CON REGALO BODA
    @OneToMany(mappedBy = "boda")
    private List<RegaloBoda> regaloBoda;

    // RELACION CON USUARIOS POR MEDIO DE INVITACION-USUARIO
    @OneToMany(mappedBy = "boda")
    private List<InvitacionUsuario> invitados;

    // RELACION CON USUARIOS POR MEDIO DE BODAS-USUARIO
    @ManyToMany(mappedBy = "boda")
    private List<BodaUsuario> novios;





}