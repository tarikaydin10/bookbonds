package dev.aydin.bookbonds.services;

import dev.aydin.bookbonds.entities.Club;
import dev.aydin.bookbonds.entities.ClubMember;
import dev.aydin.bookbonds.entities.ClubRole;
import dev.aydin.bookbonds.entities.User;

import java.util.List;
import java.util.Optional;

public interface ClubMemberService {
    void saveAllClubMembers(List<ClubMember> clubMembers);

    ClubRole getUserRoleInClub(long userId, Long clubId);

    Optional<ClubMember> getByUserAndClub(User requester, Club club);

    void deleteAllClubMembers(List<ClubMember> clubMembersToRemove);
}
