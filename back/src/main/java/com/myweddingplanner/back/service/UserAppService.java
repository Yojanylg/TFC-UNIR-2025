package com.myweddingplanner.back.service;

import com.myweddingplanner.back.dto.users.ListUserInvitationDTO;
import com.myweddingplanner.back.dto.users.MyUserDTO;
import com.myweddingplanner.back.dto.users.ListUserPresentDTO;
import com.myweddingplanner.back.model.UserApp;

import java.util.Optional;

public interface UserAppService {

    // Metodos para GET en UserAppCongtroller

    MyUserDTO getMyUser(Long userId);
    ListUserInvitationDTO getListUserInvitation(Long userId);
    ListUserPresentDTO getListUserPresent(Long userId);

    // Metodos para UPDATE en UserAppController

    MyUserDTO updateMyUser(MyUserDTO dto);
    ListUserInvitationDTO updateListUserInvitation(ListUserInvitationDTO dto);
    ListUserPresentDTO updateListUserPresent(ListUserPresentDTO dto);



    UserApp save(UserApp entity);



    Optional<UserApp> findByEmail (String email);

    boolean existsByEmail (String email);

}
