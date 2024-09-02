package dev.aydin.bookbonds.controller;

import dev.aydin.bookbonds.dto.CreateJourneyDTO;
import dev.aydin.bookbonds.dto.DTOMapper;
import dev.aydin.bookbonds.dto.JourneyFlatDTO;
import dev.aydin.bookbonds.entities.*;
import dev.aydin.bookbonds.services.BookService;
import dev.aydin.bookbonds.services.ClubService;
import dev.aydin.bookbonds.services.JourneyService;
import dev.aydin.bookbonds.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/api/v1/journeys")
@RequiredArgsConstructor
public class JourneyController {

    private final JourneyService journeyService;
    private final UserService userService;
    private final ClubService clubService;
    private final BookService bookService;
    private final DTOMapper dtoMapper;

    @PostMapping
    public ResponseEntity<JourneyFlatDTO> createJourney(@RequestHeader("Authorization") String authorizationHeader,
                                                    @RequestBody CreateJourneyDTO journeyDto) {
        User user = userService.getUserByHeader(authorizationHeader);

        // Club anhand der Club-ID abrufen und in die Journey-Entität setzen
        Club club = clubService.getClubById(journeyDto.getClubId());
        if(club == null) throw new RuntimeException("Club not found with ID: " + journeyDto.getClubId());

        // Buch anhand der ISBN-13 abrufen und in die Journey-Entität setzen
        Book book = bookService.findBookByIsbn13(journeyDto.getBookId());

        Journey createdJourney = journeyService.createJourney(journeyDto,club, book);

        return ResponseEntity.ok(dtoMapper.convertToJourneyFlatDTO(createdJourney));
    }


    @GetMapping("/{journeyId}")
    public ResponseEntity<JourneyFlatDTO> getJourneyById(@PathVariable Long journeyId) {
        Journey journey = journeyService.findById(journeyId);

        return journey != null ? ResponseEntity.ok(dtoMapper.convertToJourneyFlatDTO(journey)) : ResponseEntity.notFound().build();

        }



}
