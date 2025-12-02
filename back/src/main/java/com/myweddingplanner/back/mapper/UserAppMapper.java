package com.myweddingplanner.back.mapper;

import com.myweddingplanner.back.dto.users.*;
import com.myweddingplanner.back.model.*;

public interface UserAppMapper {

    MyUserDTO toMyUserDTO(UserApp user);
    UserWeddingDTO toMyWedding(Wedding wedding);


    ListUserPresentDTO toListUserPresent(UserApp user);
    UserPresentDTO toMyPresent(Present present);


    ListUserInvitationDTO toListUserInvitation(UserApp user);
    UserInvitationDTO toMyInvitation(UserInvitation userInvitation);
    UserCompanionDTO toMyCompanion(Companion companion);

}
