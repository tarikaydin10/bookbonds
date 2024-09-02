package dev.aydin.bookbonds.repositories;

import dev.aydin.bookbonds.entities.Chapter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChapterRepository extends JpaRepository<Chapter, Long> {


}
