package dev.aydin.bookbonds.services.impl;

import dev.aydin.bookbonds.entities.Club;
import dev.aydin.bookbonds.entities.ClubMember;
import dev.aydin.bookbonds.entities.ClubRole;
import dev.aydin.bookbonds.entities.User;
import dev.aydin.bookbonds.repositories.ClubMemberRepository;
import dev.aydin.bookbonds.repositories.ClubRepository;
import dev.aydin.bookbonds.services.ClubService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClubServiceImpl implements ClubService {

    private final ClubRepository clubRepository;
    private final ClubMemberRepository clubMemberRepository;
    @Override
    public Club createClub(Club club, User host) {
        // Setze den ClubMember
        ClubMember member = new ClubMember();
        member.setUser(host);
        member.setRole(ClubRole.HOST);
        member.setClub(club); // Verknüpfe den Club mit dem ClubMember

        // Füge den ClubMember dem Club hinzu
        club.getMembers().add(member);

        // Speichere den Club, was auch den ClubMember speichert
        return clubRepository.save(club);
    }

    @Override
    public Club getClubById(Long id) {
        return clubRepository.findById(id).orElse(null);
    }


    @Override
    public List<Club> getClubsByUser(User member){
    return clubRepository.findAllByMembers_User(member);
    }

    @Override
    public void deleteClub(Long id) {
        clubRepository.deleteById(id);
    }

    @Override
    public Club saveClub(Club club) {
        return clubRepository.save(club);
    }


}
