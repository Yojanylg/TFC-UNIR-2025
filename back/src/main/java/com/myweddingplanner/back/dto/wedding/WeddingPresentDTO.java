package com.myweddingplanner.back.dto.wedding;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class WeddingPresentDTO {

    private Long idPresent;
    private Long idProduct;
    private String productName;
    private boolean confirm;
    private double price;
    private String userName;
    private String userFirstSurname;

}
