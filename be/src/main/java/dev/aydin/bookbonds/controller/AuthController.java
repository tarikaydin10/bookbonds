package dev.aydin.bookbonds.controller;

import dev.aydin.bookbonds.dto.*;
import dev.aydin.bookbonds.services.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final DTOMapper dtoMapper;
    @PostMapping("/signup")
    public ResponseEntity<UserFlatDTO> signUp(@RequestBody SignUpRequest signUpRequest){
        return ResponseEntity.ok(dtoMapper.convertToUserFlatDTO(authService.signUp(signUpRequest)));
    }

    @PostMapping("/signin")
    public ResponseEntity<JwtAuthenticationResponse> signIn(@RequestBody SignInRequest signInRequest){
        return ResponseEntity.ok(authService.signIn(signInRequest));
    }

    @PostMapping("/refresh")
    public ResponseEntity<JwtAuthenticationResponse> refresh(@RequestBody RefreshTokenRequest refreshTokenRequest){
        return ResponseEntity.ok(authService.refreshToken(refreshTokenRequest));
    }
}
