package com.myweddingplanner.back.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "usuarios")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    @Column(name = "apellido_1")
    private String apellido1;

    @Column(name = "apellido_2")
    private String apellido2;

    private String email;

    private String telefono;

    // RELACIONES

    @OneToMany(mappedBy = "usuario")
    private List<UsuarioAlergeno> alergenos;

    // RELACION CON BODA POR MEDIO DE BODA-USUARIO
    @OneToMany(mappedBy = "usuario")
    private List<BodaUsuario> bodas;


    // RELACION CON BODA POR MEDIO DE INVITACION-USUARIO
    @OneToMany(mappedBy = "usuario")
    private List<InvitacionUsuario> invitaciones;





}