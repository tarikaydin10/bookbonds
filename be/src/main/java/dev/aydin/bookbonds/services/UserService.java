package dev.aydin.bookbonds.services;

import dev.aydin.bookbonds.entities.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService {

    User getUserById(long id);

        UserDetailsService userDetailsService();

    User getByUsername(String username);

    User getUserByToken(String token);

    User getUserByHeader(String authorizationHeader);
}
