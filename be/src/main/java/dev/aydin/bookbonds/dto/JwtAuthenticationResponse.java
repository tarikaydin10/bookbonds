package dev.aydin.bookbonds.dto;

import lombok.Data;

@Data
public class JwtAuthenticationResponse {

    private String token;

    private String refreshToken;
}
