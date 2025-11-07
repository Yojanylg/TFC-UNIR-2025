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
@ToString(exclude = {"events", "presents","guests","couple"})
public class Wedding {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    private LocalDateTime dateWedding;

    private String place;

    @Enumerated(EnumType.STRING)
    private StateWedding stateWedding;

    // RELACION CON ITINERARIO
    @OneToMany(mappedBy = "wedding", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Event> events = new ArrayList<>();

    // RELACION CON REGALO
    @OneToMany(mappedBy = "wedding", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Present> presents = new ArrayList<>();

    // RELACION CON INVITADOS
    @OneToMany(mappedBy = "wedding", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UserInvitationWedding> guests = new ArrayList<>();

    // RELACION CON NOVIOS
    @OneToMany(mappedBy = "wedding", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UserWedding> couple = new ArrayList<>();



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


    public void setGuests(List<UserInvitationWedding> nuevos){
        this.guests.clear();
        if (nuevos != null){
            nuevos.forEach(this::addGests);
        }
    }

    public void addGests(UserInvitationWedding invitacion){
        invitacion.setWedding(this);
        this.guests.add(invitacion);
    }


    public void removGuests(UserInvitationWedding invitacion){
        this.guests.remove(invitacion);
        invitacion.setWedding(null);
    }


    public void setCouple(List<UserWedding> nuevos){
        this.couple.clear();
        if (nuevos != null){
            nuevos.forEach(this::addNovio);
        }
    }

    public void addNovio(UserWedding weddingGroom){
        weddingGroom.setWedding(this);
        this.couple.add(weddingGroom);
    }

    public void removeNovio(UserWedding weddingGroom){
        this.couple.remove(weddingGroom);
        weddingGroom.setWedding(null);
    }

}