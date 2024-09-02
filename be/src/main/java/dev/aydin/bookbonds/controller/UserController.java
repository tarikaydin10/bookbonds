package dev.aydin.bookbonds.controller;

import dev.aydin.bookbonds.dto.DTOMapper;
import dev.aydin.bookbonds.dto.UserFlatDTO;
import dev.aydin.bookbonds.entities.User;
import dev.aydin.bookbonds.repositories.BookRepository;
import dev.aydin.bookbonds.repositories.UserRepository;
import dev.aydin.bookbonds.services.BookService;
import dev.aydin.bookbonds.services.JWTService;
import dev.aydin.bookbonds.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {


    @Autowired
    private final JWTService jwtService;

    @Lazy
    @Autowired
    private final UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private BookService bookService;

    @Autowired
    private DTOMapper dtoMapper;

    @GetMapping
    public ResponseEntity<UserFlatDTO> getUserDetails(@RequestHeader("Authorization") String authorizationHeader){
        String token = authorizationHeader.replace("Bearer ", "");

        // Verifiziere den Token und extrahiere den Benutzernamen
        String username = jwtService.extractUsername(token);

    User user = userService.getByUsername(username);
        return ResponseEntity.ok(dtoMapper.convertToUserFlatDTO(user));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserFlatDTO> getUserDetails(@RequestHeader("Authorization") String authorizationHeader,
                                                  @PathVariable long id){
        User user = userService.getUserById(id);
        return ResponseEntity.ok(dtoMapper.convertToUserFlatDTO(user));
    }

}
