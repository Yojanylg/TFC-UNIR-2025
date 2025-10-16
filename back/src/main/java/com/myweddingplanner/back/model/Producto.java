package com.myweddingplanner.back.model;

import jakarta.persistence.*;
import lombok.*;

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

    @OneToMany(mappedBy = "producto")
    private List<ImagenProducto> imagenes;



}