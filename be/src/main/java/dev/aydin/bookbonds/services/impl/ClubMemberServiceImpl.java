package dev.aydin.bookbonds.services.impl;

import dev.aydin.bookbonds.entities.Club;
import dev.aydin.bookbonds.entities.ClubMember;
import dev.aydin.bookbonds.entities.ClubRole;
import dev.aydin.bookbonds.entities.User;
import dev.aydin.bookbonds.repositories.ClubMemberRepository;
import dev.aydin.bookbonds.services.ClubMemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClubMemberServiceImpl implements ClubMemberService {

    private final ClubMemberRepository clubMemberRepository;

    @Override
    public void saveAllClubMembers(List<ClubMember> clubMembers) {

        clubMemberRepository.saveAll(clubMembers);
    }

    @Override
    public ClubRole getUserRoleInClub(long userId, Long clubId) {
        return ClubRole.MEMBER;
    }

    @Override
    public Optional<ClubMember> getByUserAndClub(User user, Club club) {
        return clubMemberRepository.findByUserAndClub(user, club);
    }

    @Override
    public void deleteAllClubMembers(List<ClubMember> clubMembersToRemove) {
        clubMemberRepository.deleteAll(clubMembersToRemove);

    }


}
