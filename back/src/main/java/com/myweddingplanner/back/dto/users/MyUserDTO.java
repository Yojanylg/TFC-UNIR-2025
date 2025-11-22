package com.myweddingplanner.back.dto.users;

import com.myweddingplanner.back.model.AllergiesUser;
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
public class MyUserDTO {

    private Long idUser;

    private String name;

    private String firstSurname;

    private String secondSurname;

    private String email;

    private AllergiesUser userAllergies;

    private List<MyWedding> myWeddings = new ArrayList<>();

}
