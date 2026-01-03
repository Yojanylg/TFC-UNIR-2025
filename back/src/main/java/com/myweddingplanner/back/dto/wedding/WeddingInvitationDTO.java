package com.myweddingplanner.back.dto.wedding;

import com.myweddingplanner.back.dto.users.UserCompanionDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class WeddingInvitationDTO {


    private Long idWedding;
    private Long idInvitation;
    private String name;
    private String firstSurname ;
    private String secondSurname;
    private boolean confirm;
    private String emailInvitation;
    private List<UserCompanionDTO> companion = new ArrayList<>();


}
