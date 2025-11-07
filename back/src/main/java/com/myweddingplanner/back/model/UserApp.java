package com.myweddingplanner.back.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users_app")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString(exclude = {"allergies"})
public class UserApp {

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

    private String phone;

    private String password;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "rol_id")
    private Rol rol;

    @OneToMany(mappedBy = "userApp", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UserWedding> myWeddings;

    @OneToMany(mappedBy = "userApp", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UserInvitationWedding> guests;

    @OneToMany(mappedBy = "userApp", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AllergiesUser> allergies = new ArrayList<>();

    @OneToMany(mappedBy = "userApp", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Present> presents;

    public void setAlergias(List<AllergiesUser> nuevas){
        this.allergies.clear();
        if (nuevas != null){
            nuevas.forEach(this::addAlergeno);
        }
    }

    public void addAlergeno(AllergiesUser alergia){
        alergia.setUserApp(this);
        this.allergies.add(alergia);
    }

    public void removeAlergeno(AllergiesUser alergia){
        this.allergies.remove(alergia);
        alergia.setUserApp(null);
    }

}