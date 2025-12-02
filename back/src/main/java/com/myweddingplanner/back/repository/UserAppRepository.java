package com.myweddingplanner.back.repository;

import com.myweddingplanner.back.model.UserApp;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserAppRepository extends JpaRepository<UserApp, Long> {

    @EntityGraph(attributePaths = "rol")
    Optional<UserApp> findByEmail(String email);

    boolean existsByEmail(String email);

    @EntityGraph(attributePaths = "rol")
    Optional<UserApp> findWithRolByEmail(String email);

}
