package dev.aydin.bookbonds.services;

import dev.aydin.bookbonds.dto.JwtAuthenticationResponse;
import dev.aydin.bookbonds.dto.RefreshTokenRequest;
import dev.aydin.bookbonds.dto.SignInRequest;
import dev.aydin.bookbonds.dto.SignUpRequest;
import dev.aydin.bookbonds.entities.User;

public interface AuthService {

    User signUp(SignUpRequest signUpRequest);


    JwtAuthenticationResponse signIn(SignInRequest signInRequest);

  JwtAuthenticationResponse refreshToken(RefreshTokenRequest refreshTokenRequest);



    }
