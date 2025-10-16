package com.myweddingplanner.back.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "productos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString(exclude = {"imagenes"})
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    private String nombre;

    private String descripcion;

    @Column(name = "enlace_compra")
    private String enlaceCompra;

    private double valor;

    @OneToMany(mappedBy = "producto", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ImagenProducto> imagenes = new ArrayList<>();


    public void setImagenes (List<ImagenProducto> nuevas){
        this.imagenes.clear();
        if (nuevas != null){
            nuevas.forEach(this::addImagen);
        }
    }

    public void addImagen(ImagenProducto img){
        img.setProducto(this);
        this.imagenes.add(img);
    }

    public void removeImagen(ImagenProducto img){
        this.imagenes.remove(img);
        img.setProducto(null);
    }

}