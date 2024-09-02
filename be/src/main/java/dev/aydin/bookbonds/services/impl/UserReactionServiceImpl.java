package dev.aydin.bookbonds.services.impl;

import dev.aydin.bookbonds.entities.UserReaction;
import dev.aydin.bookbonds.repositories.ChapterRepository;
import dev.aydin.bookbonds.repositories.UserReactionRepository;
import dev.aydin.bookbonds.repositories.UserRepository;
import dev.aydin.bookbonds.services.UserReactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserReactionServiceImpl implements UserReactionService {


    private final UserReactionRepository reactionRepository;

    private final ChapterRepository chapterRepository;
    private final UserRepository userRepository;
    public UserReaction addReaction(Long chapterId, Long userId, boolean isRead, boolean isFavorited) {
        UserReaction reaction = new UserReaction();
        reaction.setUser(userRepository.findById(userId).orElseThrow());
        reaction.setRead(isRead);
        reaction.setFavorited(isFavorited);
        return reactionRepository.save(reaction);
    }



}
