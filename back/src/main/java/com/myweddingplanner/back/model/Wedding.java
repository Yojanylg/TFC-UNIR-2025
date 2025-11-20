package com.myweddingplanner.back.model;

import com.myweddingplanner.back.model.enums.StateWedding;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "weddings")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString(exclude = {"events", "presents","invitations","grooms"})
public class Wedding {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @Column(name = "date_wedding")
    private LocalDateTime dateWedding;

    private String place;

    @Enumerated(EnumType.STRING)
    @Column(name = "state_wedding")
    private StateWedding stateWedding;

    // RELACION CON ITINERARIO
    @OneToMany(mappedBy = "wedding", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Event> events = new ArrayList<>();

    // RELACION CON REGALO
    @OneToMany(mappedBy = "wedding", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Present> presents = new ArrayList<>();

    // RELACION CON INVITADOS
    @OneToMany(mappedBy = "wedding", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UserInvitationWedding> invitations = new ArrayList<>();

    // RELACION CON NOVIOS
    @OneToMany(mappedBy = "wedding", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UserWedding> grooms = new ArrayList<>();



    public void setPresents(List<Present> nuevos){
        this.presents.clear();
        if (nuevos != null){
            nuevos.forEach(this::addPresent);
        }
    }

    public void addPresent(Present regalo){
        regalo.setWedding(this);
        this.presents.add(regalo);
    }

    public void removePresent(Present regalo){
        this.presents.remove(regalo);
        regalo.setWedding(null);
    }


    public void setInvitations(List<UserInvitationWedding> nuevos){
        this.invitations.clear();
        if (nuevos != null){
            nuevos.forEach(this::addGests);
        }
    }

    public void addGests(UserInvitationWedding invitacion){
        invitacion.setWedding(this);
        this.invitations.add(invitacion);
    }


    public void removGuests(UserInvitationWedding invitacion){
        this.invitations.remove(invitacion);
        invitacion.setWedding(null);
    }


    public void setGrooms(List<UserWedding> nuevos){
        this.grooms.clear();
        if (nuevos != null){
            nuevos.forEach(this::addNovio);
        }
    }

    public void addNovio(UserWedding weddingGroom){
        weddingGroom.setWedding(this);
        this.grooms.add(weddingGroom);
    }

    public void removeNovio(UserWedding weddingGroom){
        this.grooms.remove(weddingGroom);
        weddingGroom.setWedding(null);
    }

}