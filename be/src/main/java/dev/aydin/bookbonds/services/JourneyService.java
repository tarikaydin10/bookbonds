package dev.aydin.bookbonds.services;


import dev.aydin.bookbonds.dto.CreateJourneyDTO;
import dev.aydin.bookbonds.entities.*;

public interface JourneyService {
    Journey createJourney(CreateJourneyDTO journeyDTO, Club club, Book book);

    Journey findById(Long journeyId);

    Journey getJourneyByChapter(Chapter chapter);

    void updateProgress(Chapter chapter);
}
