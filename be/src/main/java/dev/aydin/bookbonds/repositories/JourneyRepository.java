package dev.aydin.bookbonds.repositories;

import dev.aydin.bookbonds.entities.Journey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface JourneyRepository extends JpaRepository<Journey, Long> {

    @Query("SELECT j FROM Journey j JOIN j.chapters c WHERE c.chapterId = :chapterId")
    Journey findJourneyByChapterId(@Param("chapterId") Long chapterId);

}
