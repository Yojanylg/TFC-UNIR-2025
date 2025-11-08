package com.myweddingplanner.back.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserWeddingDTO {

    private Long idUser;
    private Long idWedding;
    private String userName;
    private String firstSurname;
    private String secondSurname;

}
