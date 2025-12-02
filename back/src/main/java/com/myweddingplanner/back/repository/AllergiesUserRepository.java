package com.myweddingplanner.back.repository;

import com.myweddingplanner.back.model.AllergiesUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface AllergiesUserRepository extends JpaRepository<AllergiesUser, Long> {

    List<AllergiesUser> findByUserAppId(Long id);
}
