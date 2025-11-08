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
public class MyPresent {

    private Long idPresent;
    private Long idWedding;
    private Long idProduct;
    private String productName;
    private boolean isConfirm;
    private double price;
}
