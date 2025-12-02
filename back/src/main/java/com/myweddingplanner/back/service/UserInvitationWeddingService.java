package com.myweddingplanner.back.service;

import com.myweddingplanner.back.dto.users.ListUserInvitationDTO;
import com.myweddingplanner.back.dto.users.UserInvitationDTO;

import java.util.List;

public interface UserInvitationWeddingService {


    ListUserInvitationDTO getMyListInvitations(Long userId);



    boolean notified(Long id);

    void updateCompanion(UserInvitationDTO userInvitationDTO);
}
