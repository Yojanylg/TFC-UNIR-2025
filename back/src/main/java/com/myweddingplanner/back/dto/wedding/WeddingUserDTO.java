package com.myweddingplanner.back.dto.wedding;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class WeddingUserDTO {

    private Long idUserWedding;
    private Long idUser;
    private String name;
    private String firstSurname;
    private String secondSurname;

}
