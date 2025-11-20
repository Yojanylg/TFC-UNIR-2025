package com.myweddingplanner.back.mapper;

import com.myweddingplanner.back.dto.wedding.*;
import com.myweddingplanner.back.model.*;


public interface WeddingMapper {

    WeddingDTO toWeddingDTO (Wedding wedding);

    ListWeddingPresentDTO toListWeddingPresentDTO(Wedding entity);

    UserWeddingDTO toUserWeddingDTO (UserWedding entity);

    MyWeddingEventDTO toMyWeddingEventDTO(Event entity);

    MyWeddingPresentDTO toMyWeddingPresentDTO(Present entity);

    MyWeddingInvitationDTO toMyWeddingInvitationDTO(UserInvitationWedding entity);


}
