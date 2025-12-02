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
public class ListWeddingPresentDTO {

    private Long idWedding;
    private List<WeddingPresentDTO> myWeddingPresent = new ArrayList<>();
    private List<WeddingUserDTO> grooms = new ArrayList<>();

}
