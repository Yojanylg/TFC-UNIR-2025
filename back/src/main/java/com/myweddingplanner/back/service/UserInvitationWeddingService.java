package com.myweddingplanner.back.service;

import com.myweddingplanner.back.dto.users.MyInvitation;

import java.util.List;

public interface UserInvitationWeddingService {

    List<MyInvitation> getMyListInvitations(Long userId);

    boolean notified(Long id);

    void updateCompanion(MyInvitation myInvitation);
}
