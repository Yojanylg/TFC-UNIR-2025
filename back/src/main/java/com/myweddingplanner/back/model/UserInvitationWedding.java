package com.myweddingplanner.back.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "users_invitations")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString(exclude = {"wedding"})
public class UserInvitationWedding {

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

    private boolean confirm;

    @Column(name = "email_invitation")
    private String emailInvitation;

    @Column(name = "child_acompanion")
    private int childAcompanion;

    @Column(name = "adult_acompanion")
    private int adultAcompanion;


}