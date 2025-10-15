package com.myweddingplanner.back.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "imagen_productos")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ImagenProducto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String enlace;
    // TODO Enum
    private String tipo;

    @ManyToOne
    @JoinColumn(name = "id_producto")
    private Producto producto;

}