package com.myweddingplanner.back.repository;

import com.myweddingplanner.back.model.UserInvitationWedding;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserInvitationWeddingRepository extends JpaRepository<UserInvitationWedding, Long> {

    List<UserInvitationWedding> findByUserAppId(Long id);
    Optional<UserInvitationWedding> findByEmailInvitation(String email);
}
