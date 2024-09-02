package dev.aydin.bookbonds.services.impl;

import dev.aydin.bookbonds.entities.*;
import dev.aydin.bookbonds.repositories.*;
import dev.aydin.bookbonds.services.ChapterService;
import dev.aydin.bookbonds.services.ClubService;
import dev.aydin.bookbonds.services.JourneyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChapterServiceImpl implements ChapterService {

    private final ChapterRepository chapterRepository;
    private final UserReactionRepository userReactionRepository;
    private final JourneyService journeyService;
    private final ClubService clubService;
    @Override
    public Chapter getChapterById(Long chapterId) {
        return chapterRepository.findById(chapterId).orElse(null);
    }

    @Override
    public Chapter addComment(Chapter chapter, Comment comment){
         chapter.getComments().add(comment);
            return chapterRepository.save(chapter);
    }

    @Override
    public void toggleAsRead(Chapter chapter, User user) {
        UserReaction reaction = findOrCreateUserReaction(chapter, user);
        reaction.setRead(!reaction.isRead());
        userReactionRepository.save(reaction);

        updateChapterProgress(chapter);
        journeyService.updateProgress(chapter);
    }

    @Override
    // Methode, um ein Kapitel als favorisiert zu markieren
    public void toggleAsFavorite(Chapter chapter, User user) {
        UserReaction reaction = findOrCreateUserReaction(chapter, user);
        reaction.setFavorited(!reaction.isFavorited());
        userReactionRepository.save(reaction);
    }

    @Override
    public void updateChapterProgress(Chapter chapter) {
        Club club = clubService.getClubById(chapter.getClubId());
        long totalClubMembers = club.getMembers().size();
        chapter.updateProgress(totalClubMembers);
        chapterRepository.save(chapter);
    }

    // Hilfsmethode, um eine neue Reaktion zu erstellen oder eine bestehende zu finden
    private UserReaction findOrCreateUserReaction(Chapter chapter, User user) {
        return chapter.getReactions().stream()
                .filter(r -> r.getUser().getUser_id() == user.getUser_id())
                .findFirst()
                .orElseGet(() -> {
                    UserReaction newReaction = new UserReaction();
                    newReaction.setUser(user);
                    chapter.getReactions().add(newReaction);
                    return newReaction;
                });
    }

}
