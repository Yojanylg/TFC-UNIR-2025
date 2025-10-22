package com.myweddingplanner.back.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "usuarios")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString(exclude = {"alergenos"})
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

    private String password;


    // RELACIONES

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UsuarioAlergeno> alergenos = new ArrayList<>();

    public void setAlergenos (List<UsuarioAlergeno> nuevas){
        this.alergenos.clear();
        if (nuevas != null){
            nuevas.forEach(this::addAlergeno);
        }
    }

    public void addAlergeno(UsuarioAlergeno alergia){
        alergia.setUsuario(this);
        this.alergenos.add(alergia);
    }

    public void removeAlergeno(UsuarioAlergeno alergia){
        this.alergenos.remove(alergia);
        alergia.setUsuario(null);
    }


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_rol")
    private Rol rol;

    // RELACION CON BODA POR MEDIO DE BODA-USUARIO
    // Relacion modelo 1
    //@OneToMany(mappedBy = "usuario")
    //private List<BodaUsuario> bodas;


    // RELACION CON BODA POR MEDIO DE INVITACION-USUARIO
    // Relacion modelo 1
    //@OneToMany(mappedBy = "usuario")
    //private List<InvitacionUsuario> invitaciones;





}