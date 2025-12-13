package com.myweddingplanner.back.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "users_invitations")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString(exclude = {"wedding", "companions"})
public class UserInvitation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserApp userApp;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "wedding_id")
    private Wedding wedding;

    @Column(name = "confirm")
    private boolean confirm;

    @Column(name = "notified")
    private boolean notified;

    @Column(name = "email_invitation")
    private String emailInvitation;


    @OneToMany(mappedBy = "userInvitationWedding")
    private List<Companion> companions;


}