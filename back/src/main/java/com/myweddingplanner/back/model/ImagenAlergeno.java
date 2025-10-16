package com.myweddingplanner.back.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "imagen_alergenos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString(exclude = {})
public class ImagenAlergeno {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;
    private String enlace;
    private String tipo;

    //bidireccional
    //@ManyToOne(fetch = FetchType.LAZY)
    //@JoinColumn(name = "id_alergeno")
    //private Alergeno alergeno;

}