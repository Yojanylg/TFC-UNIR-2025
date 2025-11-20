package com.myweddingplanner.back.dto.wedding;

import com.myweddingplanner.back.dto.users.MyCompanion;
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
public class MyWeddingInvitationDTO {

    private Long idInvitation;
    private String name;
    private String firstSurname ;
    private String secondSurname;
    private boolean isConfirm;
    private String emailInvitation;
    private List<MyCompanion> companion = new ArrayList<>();


}
