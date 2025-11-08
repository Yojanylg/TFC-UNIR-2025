package com.myweddingplanner.back.dto;

import com.myweddingplanner.back.dto.wedding.MyWeddingEventDTO;
import com.myweddingplanner.back.dto.wedding.MyWeddingInvitationDTO;
import com.myweddingplanner.back.dto.wedding.MyWeddingPresentDTO;
import com.myweddingplanner.back.model.Event;
import com.myweddingplanner.back.model.enums.StateWedding;
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

    private Long id;

    private LocalDateTime dateWedding;

    private String place;

    private StateWedding stateWedding;

    private List<MyWeddingEventDTO> events = new ArrayList<>();

    private List<MyWeddingEventDTO> grooms = new ArrayList<>();

    private List<MyWeddingPresentDTO> presents = new ArrayList<>();

    private List<MyWeddingInvitationDTO> invitations = new ArrayList<>();


}
