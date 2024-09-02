package dev.aydin.bookbonds.services.impl;

import dev.aydin.bookbonds.entities.User;
import dev.aydin.bookbonds.repositories.UserRepository;
import dev.aydin.bookbonds.services.JWTService;
import dev.aydin.bookbonds.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    @Autowired
    private final UserRepository userRepository;
    @Autowired
    private final JWTService jwtService;



    @Override
    public User getUserById(long id){
        return userRepository.findById(id)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

    }

    @Override
    public User getByUsername(String username){
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }


    @Override
    public User getUserByToken(String token) {
        String username = jwtService.extractUsername(token);
        // Finde den Benutzer anhand des Benutzernamens
        return getByUsername(username);
    }

    @Override
    public User getUserByHeader(String authorizationHeader) {
        // Extrahiere den JWT-Token aus dem Authorization Header
        String token = authorizationHeader.replace("Bearer ", "");

        // Verifiziere den Token und extrahiere den Benutzernamen
        String username = jwtService.extractUsername(token);
        // Finde den Benutzer anhand des Benutzernamens
        return getByUsername(username);
    }


    public UserDetailsService userDetailsService(){
        return new UserDetailsService() {
            public UserDetails loadUserByUsername(String username) {
                return userRepository.findByUsername(username)
                        .orElseThrow(() -> new UsernameNotFoundException("User not found"));
            }
        };
    }



}
