package com.myweddingplanner.back.dto.wedding;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class WeddingDTO {

    private Long idWedding;

    private LocalDate dateWedding;

    private String place;

    private String stateWedding;

    private List<WeddingEventDTO> events = new ArrayList<>();

    private List<WeddingUserDTO> grooms = new ArrayList<>();

}
