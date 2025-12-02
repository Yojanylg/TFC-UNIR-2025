package com.myweddingplanner.back.mapper;

import com.myweddingplanner.back.dto.wedding.*;
import com.myweddingplanner.back.model.*;


public interface WeddingMapper {

    WeddingDTO toWeddingDTO (Wedding wedding);

    ListWeddingPresentDTO toListWeddingPresentDTO(Wedding entity);

    WeddingUserDTO toUserWeddingDTO (UserWedding entity);

    WeddingEventDTO toMyWeddingEventDTO(Event entity);

    WeddingPresentDTO toMyWeddingPresentDTO(Present entity);

    WeddingInvitationDTO toMyWeddingInvitationDTO(UserInvitation entity);

    ListWeddingInvitationDTO toListWeddingInvitationDTO (Wedding entity);


}
