package com.myweddingplanner.back.mapper;

import com.myweddingplanner.back.dto.users.*;
import com.myweddingplanner.back.model.*;

public interface UserAppMapper {

    MyUserDTO toMyUserDTO(UserApp user);
    MyWedding toMyWedding(Wedding wedding);


    ListUserPresentDTO toListUserPresent(UserApp user);
    MyPresent toMyPresent(Present present);


    ListUserInvitationDTO toListUserInvitation(UserApp user);
    MyInvitation toMyInvitation(UserInvitationWedding userInvitationWedding);
    MyCompanion toMyCompanion(Companion companion);

}
