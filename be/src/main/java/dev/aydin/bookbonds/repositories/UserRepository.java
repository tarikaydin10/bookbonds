package dev.aydin.bookbonds.repositories;

import dev.aydin.bookbonds.entities.Role;
import dev.aydin.bookbonds.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {



    Optional<User> findByUsername(String username);

    User findByRole(Role role);


}
