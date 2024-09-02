package dev.aydin.bookbonds.services;

import dev.aydin.bookbonds.entities.Club;
import dev.aydin.bookbonds.entities.User;

import java.util.List;

public interface ClubService {
 Club createClub(Club club, User host);
 Club getClubById(Long id);

 void deleteClub(Long id);

 Club saveClub(Club club);

 List<Club> getClubsByUser(User user);
}
