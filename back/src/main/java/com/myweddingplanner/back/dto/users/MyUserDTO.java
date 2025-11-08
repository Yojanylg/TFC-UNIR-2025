package com.myweddingplanner.back.dto.users;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MyUserDTO {

    private Long idUser;
    private String name;
    private String firstSurname;
    private String secondSurname;
    private String email;

}
