package dev.aydin.bookbonds.services.impl;
import dev.aydin.bookbonds.dto.CreateJourneyDTO;
import dev.aydin.bookbonds.entities.*;
import dev.aydin.bookbonds.repositories.ChapterRepository;
import dev.aydin.bookbonds.repositories.JourneyRepository;
import dev.aydin.bookbonds.services.BookService;
import dev.aydin.bookbonds.services.ClubService;
import dev.aydin.bookbonds.services.JourneyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JourneyServiceImpl implements JourneyService {

    private final JourneyRepository journeyRepository;
    private final ClubService clubService;
    private final BookService bookService;
    private final ChapterRepository chapterRepository;

    @Override
    public Journey createJourney(CreateJourneyDTO dto, Club club, Book book) {


        Journey journey = new Journey();
        journey.setTitle(dto.getTitle());
        journey.setDescription(dto.getDescription());
        journey.setBook(book);
        journey.setNumberOfChapters(dto.getNumberOfChapters());



        // Erstelle Kapitel basierend auf der Anzahl der Kapitel im DTO
        for (int i = 1; i <= journey.getNumberOfChapters(); i++) {
            Chapter chapter = new Chapter();
            chapter.setTitle("Chapter " + i);
            chapter.setChapterNumber(i);
            chapter.setClubId(club.getClubId());
            chapterRepository.save(chapter);
            journey.getChapters().add(chapter);
        }

        journey = journeyRepository.save(journey);

        club.getJourneys().add(journey);
        clubService.saveClub(club);

        return journey;
    }

    @Override
    public Journey findById(Long journeyId) {
        return journeyRepository.findById(journeyId).orElse(null);
    }

    @Override
    public Journey getJourneyByChapter(Chapter chapter) {
        return journeyRepository.findJourneyByChapterId(chapter.getChapterId());
    }

    @Override
    public void updateProgress(Chapter chapter) {
        Journey journey = getJourneyByChapter(chapter);
        journey.updateProgress();
        journeyRepository.save(journey);
    }
}

