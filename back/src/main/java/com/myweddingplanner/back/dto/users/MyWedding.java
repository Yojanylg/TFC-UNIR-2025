package com.myweddingplanner.back.dto.users;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MyWedding {

    private Long idGroom;
    private Long idWedding;
    private String stateWedding;
    private LocalDateTime date;

}
