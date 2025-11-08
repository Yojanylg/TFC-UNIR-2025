package com.myweddingplanner.back.dto.users;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MyUserPresentDTO {

    private Long userId;
    private List<MyPresent> myPresents;
}
