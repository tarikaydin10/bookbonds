package dev.aydin.bookbonds.repositories;

import dev.aydin.bookbonds.entities.Club;
import dev.aydin.bookbonds.entities.ClubMember;
import dev.aydin.bookbonds.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ClubMemberRepository extends JpaRepository<ClubMember, Long> {

    List<ClubMember> findAllByClub_ClubId(Long clubId);

    List<ClubMember> findAllByUser(User user);

    Optional<ClubMember> findByUserAndClub(User user, Club club);

    void deleteByUserAndClub_ClubId(User user, Long clubId);
}
