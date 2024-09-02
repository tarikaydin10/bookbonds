package dev.aydin.bookbonds.services.impl;

import dev.aydin.bookbonds.dto.JwtAuthenticationResponse;
import dev.aydin.bookbonds.dto.RefreshTokenRequest;
import dev.aydin.bookbonds.dto.SignInRequest;
import dev.aydin.bookbonds.dto.SignUpRequest;
import dev.aydin.bookbonds.entities.Role;
import dev.aydin.bookbonds.entities.User;
import dev.aydin.bookbonds.repositories.UserRepository;
import dev.aydin.bookbonds.services.AuthService;
import dev.aydin.bookbonds.services.JWTService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

        private final UserRepository userRepository;

        private final PasswordEncoder passwordEncoder;

        private final AuthenticationManager authenticationManager;

        private final JWTService jwtService;

        public User signUp(SignUpRequest signUpRequest){
            User user = new User();
            user.setUsername(signUpRequest.getUsername());
            user.setFirstname(signUpRequest.getFirstname());
            user.setLastname(signUpRequest.getLastname());
            user.setEmail(signUpRequest.getEmail());
            user.setRole(Role.USER);
            user.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));
           return userRepository.save(user);
        }

        public JwtAuthenticationResponse signIn(SignInRequest signInRequest){
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(signInRequest.getUsername(),
                    signInRequest.getPassword()));

            var user = userRepository.findByUsername(signInRequest.getUsername()).orElseThrow(() -> new IllegalArgumentException("Invalid Email or Password"));
            var jwt = jwtService.generateToken(user);
            var refreshToken = jwtService.generateRefreshToken(new HashMap<>(), user);


            JwtAuthenticationResponse jwtAuthenticationResponse = new JwtAuthenticationResponse();

            jwtAuthenticationResponse.setToken(jwt);
            jwtAuthenticationResponse.setRefreshToken(refreshToken);
            return jwtAuthenticationResponse;

        }


        public JwtAuthenticationResponse refreshToken(RefreshTokenRequest refreshTokenRequest){
            String username = jwtService.extractUsername(refreshTokenRequest.getToken());
            User user = userRepository.findByUsername(username).orElseThrow();
            if(jwtService.isTokenValid(refreshTokenRequest.getToken(), user)){
                var jwt = jwtService.generateToken(user);

                JwtAuthenticationResponse jwtAuthenticationResponse = new JwtAuthenticationResponse();

                jwtAuthenticationResponse.setToken(jwt);
                jwtAuthenticationResponse.setRefreshToken(refreshTokenRequest.getToken());
                return jwtAuthenticationResponse;
            }
            return null;
        }
}
