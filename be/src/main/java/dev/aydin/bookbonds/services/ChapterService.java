package dev.aydin.bookbonds.services;


import dev.aydin.bookbonds.entities.Chapter;
import dev.aydin.bookbonds.entities.Comment;
import dev.aydin.bookbonds.entities.User;

public interface ChapterService {

    Chapter getChapterById(Long chapterId);

    Chapter addComment(Chapter chapter, Comment comment);

    void toggleAsRead(Chapter chapter, User user);
    void toggleAsFavorite(Chapter chapter, User user);

    void updateChapterProgress(Chapter chapter);
}
