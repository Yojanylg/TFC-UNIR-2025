package com.myweddingplanner.back.model;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "companions")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString(exclude = {"userInvitationWedding"})
public class Companion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    private String name;

    @Column(name = "first_surname")
    private String firstSurname;

    @Column(name = "second_surname")
    private String secondSurname;

    private String email;

    @Column(name = "adult")
    private boolean isAdult;

    private String allergies;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_invitation_id")
    private UserInvitationWedding userInvitationWedding;
}
