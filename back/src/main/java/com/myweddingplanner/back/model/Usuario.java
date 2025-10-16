package com.myweddingplanner.back.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "usuarios")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString(exclude = {"bodas", "invitaciones"})
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
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