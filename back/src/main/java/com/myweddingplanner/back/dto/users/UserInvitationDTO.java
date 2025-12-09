package com.myweddingplanner.back.dto.users;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserInvitationDTO {

    private Long idInvitation;
    private String weddingDate;
    private String place;
    private String couple;
    private boolean isConfirm;
    private boolean isNotified;
    private List<UserCompanionDTO> companions = new ArrayList<>();

}
