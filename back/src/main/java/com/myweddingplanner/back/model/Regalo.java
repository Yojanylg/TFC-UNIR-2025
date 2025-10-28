package com.myweddingplanner.back.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "regalos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString(exclude = {"boda"})
public class Regalo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @Column(name = "id_producto")
    private Long idProducto;

    private String descripcion;

    @Column(name = "enlace_compra")
    private String enlaceCompra;

    @Column(name = "comprador")
    private Long idUsuario;

    private double valor;

    private boolean confirmado;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_boda")
    private Boda boda;

}