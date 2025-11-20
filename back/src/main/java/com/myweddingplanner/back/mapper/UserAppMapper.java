package com.myweddingplanner.back.mapper;

import com.myweddingplanner.back.dto.users.*;
import com.myweddingplanner.back.model.UserApp;

public interface UserAppMapper {

    MyUserAllergiesDTO toMyUserAllergiesDTO (UserApp u);

    MyUserPresentDTO toMyUserPresentDTO (UserApp u);

    MyUserDTO toMyUserDTO(UserApp u);


}
