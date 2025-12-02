package com.myweddingplanner.back.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "allergies")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString(exclude = {"userApp"})
public class AllergiesUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    @Column(name = "id")
    private Long id;

    private boolean gluten;

    private boolean crustaceos;

    private boolean huevos;

    private boolean pescado;

    private boolean cacahuete;

    private boolean soja;

    private boolean leche;

    private boolean frutosCascara;

    private boolean apio;

    private boolean mostaza;

    private boolean sesamo;

    private boolean sulfitos;

    private boolean altramuces;

    private boolean moluscos;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private UserApp userApp;

}
