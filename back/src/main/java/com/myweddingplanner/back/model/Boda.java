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
public class Boda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String lugar;

    private String fecha;

    @OneToOne(mappedBy = "boda")
    private Itinerario itinerario;

    @OneToMany(mappedBy = "boda")
    private List<RegaloBoda> regaloBoda;

    @OneToMany(mappedBy = "boda")
    private List<InvitacionUsuario> invitados;






}