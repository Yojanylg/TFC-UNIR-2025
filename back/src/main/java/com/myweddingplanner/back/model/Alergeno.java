package com.myweddingplanner.back.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "alergenos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString(exclude = {"imagenes"})
public class Alergeno {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    private String nombre;

    @OneToMany(mappedBy = "alergeno", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ImagenAlergeno> imagenes = new ArrayList<>();

    public void setImagenes (List<ImagenAlergeno> nuevas){
        this.imagenes.clear();
        if (nuevas != null){
            nuevas.forEach(this::addImagen);
        }
    }

    public void addImagen(ImagenAlergeno img){
        img.setAlergeno(this);
        this.imagenes.add(img);
    }

    public void removeImagen(ImagenAlergeno img){
        this.imagenes.remove(img);
        img.setAlergeno(null);
    }


}