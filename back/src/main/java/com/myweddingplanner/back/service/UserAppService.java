package com.myweddingplanner.back.service;

import com.myweddingplanner.back.dto.users.MyUserAllergiesDTO;
import com.myweddingplanner.back.dto.users.MyUserDTO;
import com.myweddingplanner.back.dto.users.ListUserPresentDTO;
import com.myweddingplanner.back.model.UserApp;

import java.util.List;
import java.util.Optional;

public interface UserAppService {

    MyUserDTO findMyUserDTOById (Long id);

    public boolean updateMyUserPresents(Long userID, ListUserPresentDTO dto);

    MyUserAllergiesDTO findMyUserAllergiesDTOById (Long id);

    ListUserPresentDTO finMyUserPresentDTOById (Long id);

    MyUserDTO update (MyUserDTO dto);

    List<UserApp> findAll();

    UserApp save(UserApp entity);

    void deleteById (Long id);

    Optional<UserApp> findByEmail (String email);

    boolean existsByEmail (String email);

    boolean updateUserAllergies(Long userId, MyUserAllergiesDTO dto);
}
