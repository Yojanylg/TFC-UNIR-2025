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
public class MyInvitation {

    private Long idInvitation;
    private LocalDateTime date;
    private String place;
    private String couple;
    private boolean isConfirm;
    private int childCompanion;
    private int adultCompanion;

}
