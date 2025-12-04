package com.myweddingplanner.back.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AuthResponse {

    private String accessToken;
    private String refreshToken;
    private String tokenType = "Bearer";

    private boolean haveNewInvitations;
    private boolean hasWedding;
    private boolean haveInvitations;

    public AuthResponse(String accessToken, String refreshToken){
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }

    public AuthResponse(String accessToken, String refreshToken, boolean haveNewInvitations, boolean hasWedding, boolean haveInvitations){
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.haveNewInvitations = haveNewInvitations;
        this.hasWedding= hasWedding;
        this.haveInvitations= haveInvitations;
    }
}
