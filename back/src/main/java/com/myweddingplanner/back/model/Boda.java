package com.myweddingplanner.back.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "bodas")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString(exclude = {"itinerario", "novios", "invitados"})
public class Boda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    private String lugar;

    private String fecha;

    // RELACION CON ITINERARIO
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "id_itinerario")
    private Itinerario itinerario;

    // RELACION CON REGALO
    @OneToMany(mappedBy = "boda", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Regalo> regalos = new ArrayList<>();

    public void setRegalos(List<Regalo> nuevos){
        this.regalos.clear();
        if (nuevos != null){
            nuevos.forEach(this::addRegalo);
        }
    }

    public void addRegalo(Regalo regalo){
        regalo.setBoda(this);
        this.regalos.add(regalo);
    }

    public void removeRegalo(Regalo regalo){
        this.regalos.remove(regalo);
        regalo.setBoda(null);
    }

    // RELACION CON INVITADOS
    @OneToMany(mappedBy = "boda", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Invitado> invitados = new ArrayList<>();

    public void setInvitados(List<Invitado> nuevos){
        this.invitados.clear();
        if (nuevos != null){
            nuevos.forEach(this::addInvitado);
        }
    }

    public void addInvitado(Invitado invitado){
        invitado.setBoda(this);
        this.invitados.add(invitado);
    }

    public void removeInvitao(Invitado invitado){
        this.invitados.remove(invitado);
        invitado.setBoda(null);
    }

    // RELACION CON USUARIOS POR MEDIO DE BODAS-USUARIO
    //@OneToMany(mappedBy = "boda", cascade = CascadeType.ALL, orphanRemoval = true)
    //private List<BodaUsuario> novios;

    // RELACION CON NOVIOS
    @OneToMany(mappedBy = "boda", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Novio> novios = new ArrayList<>();

    public void setNovios(List<Novio> nuevos){
        this.novios.clear();
        if (nuevos != null){
            nuevos.forEach(this::addNovio);
        }
    }

    public void addNovio(Novio novio){
        novio.setBoda(this);
        this.novios.add(novio);
    }

    public void removeNovio(Novio novio){
        this.novios.remove(novio);
        novio.setBoda(null);
    }

}