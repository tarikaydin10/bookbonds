package dev.aydin.bookbonds.controller;

import dev.aydin.bookbonds.dto.*;
import dev.aydin.bookbonds.entities.*;
import dev.aydin.bookbonds.services.ClubMemberService;
import dev.aydin.bookbonds.services.ClubService;
import dev.aydin.bookbonds.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/clubs")
@RequiredArgsConstructor
public class ClubController {

    private final ClubService clubService;
    private final UserService userService;
    private final ClubMemberService clubMemberService;
    private final DTOMapper dtoMapper;


    @PostMapping(consumes = "multipart/form-data")
    public ResponseEntity<ClubDTO> createClub( @RequestHeader("Authorization") String authorizationHeader,
                                               @RequestPart("title") String title,
                                               @RequestPart(value = "description", required = false) String description,
                                               @RequestPart(value = "theme", required = false) String theme,
                                               @RequestPart(value = "image", required = false) MultipartFile image) {

        User host = userService.getUserByHeader(authorizationHeader);
        Club club = new Club();
        club.setTitle(title);
        club.setDescription(description);
        club.setTheme(theme);
        Club createdClub = clubService.createClub(club, host);

        return ResponseEntity.ok(dtoMapper.convertToClubDTO(createdClub));
    }


    @GetMapping("/{id}")
    public ResponseEntity<ClubDTO> getClubById(@PathVariable Long id) {
        Club club = clubService.getClubById(id);
        return club != null? ResponseEntity.ok(dtoMapper.convertToClubDTO(club)) : ResponseEntity.notFound().build();
    }


    @GetMapping
    public ResponseEntity<List<ClubDTO>> getUserClubs(@RequestHeader("Authorization") String authorizationHeader) {
        try {
            User user = userService.getUserByHeader(authorizationHeader);
            List<ClubDTO> clubDTOs = clubService.getClubsByUser(user)
                    .stream()
                    .map(dtoMapper::convertToClubDTO) // Wandelt jeden Club in ein ClubDTO um
                    .collect(Collectors.toList());
            return ResponseEntity.ok(clubDTOs);
        } catch (UsernameNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }




    @GetMapping("/hosted")
    public ResponseEntity<List<Club>> getClubsByHost(@RequestHeader("Authorization") String authorizationHeader) {
        try{
            return null;
        } catch (UsernameNotFoundException e){
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/member")
    public ResponseEntity<List<Club>> getClubsByMember(@RequestHeader("Authorization") String authorizationHeader) {
        try{
            return null;
        } catch (UsernameNotFoundException e){
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClub(@RequestHeader("Authorization") String authorizationHeader,
                                           @PathVariable Long id) {
        Club club = clubService.getClubById(id);
        if (club== null) {
            return ResponseEntity.notFound().build();
        }


        User requester = userService.getUserByHeader(authorizationHeader);



        Optional<ClubMember> clubMemberOptional = clubMemberService.getByUserAndClub(requester, club);
        if (clubMemberOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        ClubRole role =  clubMemberOptional.get().getRole();

        if(role != ClubRole.HOST){
            return ResponseEntity.badRequest().build();
        }
        clubService.deleteClub(id);
        return ResponseEntity.noContent().build();
    }

    //ClubDTO
    // überprüfe. ob Anfrager Host des Clubs ist
    @PutMapping("/{clubId}/add-members")
    public ResponseEntity<ClubDTO> addMembers(@RequestHeader("Authorization") String authorizationHeader,
                                              @PathVariable Long clubId,
                                              @RequestBody Set<Long> userIds) {
        Club club = clubService.getClubById(clubId);
        if (club == null) {
            return ResponseEntity.notFound().build();
        }


        User requester = userService.getUserByHeader(authorizationHeader);



        Optional<ClubMember> clubMemberOptional = clubMemberService.getByUserAndClub(requester, club);
        if (clubMemberOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        ClubRole role =  clubMemberOptional.get().getRole();

        if(role != ClubRole.HOST){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        Set<User> users = userIds.stream()
                .map(userId -> {
                    try {
                        return userService.getUserById(userId);
                    } catch (UsernameNotFoundException ex) {
                        // Den Fehler loggen, wenn gewünscht
                        System.out.println("User with ID " + userId + " not found: " + ex.getMessage());
                        return null; // Null zurückgeben, wenn der Benutzer nicht gefunden wurde
                    }
                })
                .filter(Objects::nonNull) // Nur die gefundenen Benutzer beibehalten
                .collect(Collectors.toSet());

        List<ClubMember> clubMembers = new ArrayList<>();
        for (User user : users) {
            ClubMember cm = new ClubMember();
            cm.setUser(user);
            cm.setRole(ClubRole.MEMBER);
            cm.setClub(club);
            clubMembers.add(cm);
        }
        club.getMembers().addAll(clubMembers);  // Hinzufügen der ClubMember Objekte
        clubMemberService.saveAllClubMembers(clubMembers); // Speichern aller ClubMember
        Club updatedClub = clubService.saveClub(club);
        return ResponseEntity.ok(dtoMapper.convertToClubDTO(updatedClub));
    }
    @PutMapping("/{clubId}/leave")
    public ResponseEntity<String> leaveClub(@RequestHeader("Authorization") String authorizationHeader,
                                            @PathVariable Long clubId) {
        User user = userService.getUserByHeader(authorizationHeader);

        // Hole den Club basierend auf der ID
        Club club = clubService.getClubById(clubId);
        if (club == null) {
            return ResponseEntity.notFound().build();
        }


        // Hole das ClubMember Objekt für den Benutzer und den Club
        Optional<ClubMember> clubMemberOptional = clubMemberService.getByUserAndClub(user, club);
        if (clubMemberOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        ClubMember clubMember = clubMemberOptional.get();
        // Entferne den Benutzer aus dem Club
        club.getMembers().remove(clubMember);

        // Lösche das ClubMember Objekt aus der Datenbank
        clubMemberService.deleteAllClubMembers(Collections.singletonList(clubMember));

        // Speichere die Änderungen am Club
        clubService.saveClub(club);

        return ResponseEntity.ok("You have successfully left the club.");
    }


    // überprüfe. ob Anfrager Host des Clubs ist
    @PutMapping("/{clubId}/delete-members")
    public ResponseEntity<Club> deleteMembers(@RequestHeader("Authorization") String authorizationHeader,
                                              @PathVariable Long clubId,
                                              @RequestBody Set<Long> userIds) {
        Club club = clubService.getClubById(clubId);
        if (club == null) {
            return ResponseEntity.notFound().build();
        }
        User requester = userService.getUserByHeader(authorizationHeader);



        Optional<ClubMember> clubMemberOptional = clubMemberService.getByUserAndClub(requester, club);
        if (clubMemberOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        ClubRole role =  clubMemberOptional.get().getRole();

        if(role != ClubRole.HOST){
            return ResponseEntity.badRequest().build();
        }

        Set<User> users = userIds.stream()
                .map(userId -> {
                    try {
                        return userService.getUserById(userId);
                    } catch (UsernameNotFoundException ex) {
                        // Den Fehler loggen, wenn gewünscht
                        System.out.println("User with ID " + userId + " not found: " + ex.getMessage());
                        return null; // Null zurückgeben, wenn der Benutzer nicht gefunden wurde
                    }
                })
                .filter(Objects::nonNull) // Nur die gefundenen Benutzer beibehalten
                .collect(Collectors.toSet());

        List<ClubMember> clubMembersToRemove = new ArrayList<>();
        for (User user : users) {
            Optional<ClubMember> memberOptional = clubMemberService.getByUserAndClub(user, club);
            memberOptional.ifPresent(clubMembersToRemove::add);
        }
        // Entfernen der Mitglieder aus dem Club
        club.getMembers().removeAll(clubMembersToRemove);

        // Die gelöschten Mitglieder aus der Datenbank entfernen
        clubMemberService.deleteAllClubMembers(clubMembersToRemove);

        Club updatedClub = clubService.saveClub(club);

        return ResponseEntity.ok(updatedClub);
    }

}
