package com.myweddingplanner.back.mapper;

import com.myweddingplanner.back.dto.users.*;
import com.myweddingplanner.back.model.UserApp;
import com.myweddingplanner.back.model.UserInvitationWedding;

public interface UserAppMapper {

    MyUserAllergiesDTO toMyUserAllergiesDTO (UserApp u);

    ListUserPresentDTO toMyUserPresentDTO (UserApp u);

    MyUserDTO toMyUserDTO(UserApp u);

    MyInvitation toMyInvitation(UserInvitationWedding ui);


}
