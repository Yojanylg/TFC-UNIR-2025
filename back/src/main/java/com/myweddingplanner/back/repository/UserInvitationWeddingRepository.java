package com.myweddingplanner.back.repository;

import com.myweddingplanner.back.model.UserInvitationWedding;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserInvitationWeddingRepository extends JpaRepository<UserInvitationWedding, Long> {

    Optional<UserInvitationWedding> findByUserId(Long id);
    Optional<UserInvitationWedding> findByEmailInvitation(String email);
}
