package com.myweddingplanner.back.repository;

import com.myweddingplanner.back.model.UserInvitation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface UserInvitationWeddingRepository extends JpaRepository<UserInvitation, Long> {

    List<UserInvitation> findByUserAppId(Long id);
    Optional<UserInvitation> findByEmailInvitation(String email);
}
