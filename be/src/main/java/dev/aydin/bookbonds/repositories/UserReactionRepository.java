package dev.aydin.bookbonds.repositories;

import dev.aydin.bookbonds.entities.UserReaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserReactionRepository extends JpaRepository<UserReaction, Long> {


}
