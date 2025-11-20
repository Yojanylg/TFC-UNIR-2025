package com.myweddingplanner.back.dto.wedding;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class WeddingDTO {

    private Long idWedding;

    private LocalDateTime dateWedding;

    private String place;

    private String stateWedding;

    private List<MyWeddingEventDTO> events = new ArrayList<>();

    private List<UserWeddingDTO> grooms = new ArrayList<>();

    private List<MyWeddingPresentDTO> presents = new ArrayList<>();

    private List<MyWeddingInvitationDTO> invitations = new ArrayList<>();


}
