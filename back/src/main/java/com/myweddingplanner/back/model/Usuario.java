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

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Alergia> alergias = new ArrayList<>();

    public void setAlergias(List<Alergia> nuevas){
        this.alergias.clear();
        if (nuevas != null){
            nuevas.forEach(this::addAlergeno);
        }
    }

    public void addAlergeno(Alergia alergia){
        alergia.setUsuario(this);
        this.alergias.add(alergia);
    }

    public void removeAlergeno(Alergia alergia){
        this.alergias.remove(alergia);
        alergia.setUsuario(null);
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_rol")
    private Rol rol;

}