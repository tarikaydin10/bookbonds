package dev.aydin.bookbonds.repositories;

import dev.aydin.bookbonds.entities.Club;
import dev.aydin.bookbonds.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClubRepository extends JpaRepository<Club, Long> {

    List<Club> findAllByMembers_User(User user);


}
