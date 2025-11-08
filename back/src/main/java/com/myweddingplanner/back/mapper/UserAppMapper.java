package com.myweddingplanner.back.mapper;

import com.myweddingplanner.back.dto.users.*;
import com.myweddingplanner.back.model.UserApp;

public interface UserAppMapper {

    MyUserDTO toMyUserDTO(UserApp u);
    MyUserAllergenDTO toMyUserAllergenDTO (UserApp u);
    MyUserPresentDTO toMyUserPresentDTO (UserApp u);
    MyUserInvitationWeddingDTO toMyUserInvitationWeddingDTO (UserApp u);
    MyUserWeddingDTO toMyUserWeddingDTO (UserApp u);

}
