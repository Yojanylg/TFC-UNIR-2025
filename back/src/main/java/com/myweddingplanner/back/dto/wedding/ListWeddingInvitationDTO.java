package com.myweddingplanner.back.dto.wedding;

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
public class ListWeddingInvitationDTO {

    private Long idWedding;
    private List<MyWeddingInvitationDTO> listWeddingInvitation = new ArrayList<>();
    private List<UserWeddingDTO> grooms = new ArrayList<>();

}
