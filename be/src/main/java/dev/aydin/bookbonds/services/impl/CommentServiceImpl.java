package dev.aydin.bookbonds.services.impl;

import dev.aydin.bookbonds.entities.*;
import dev.aydin.bookbonds.repositories.*;
import dev.aydin.bookbonds.services.ChapterService;
import dev.aydin.bookbonds.services.CommentService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {


    private final CommentRepository commentRepository;
    private final ChapterRepository chapterRepository;

    private final UserRepository userRepository;

    private final ChapterService chapterService;

    @Override
    public Comment createComment(String content, User author, Chapter chapter, Comment parentComment) {
        Comment comment = new Comment();
        comment.setUser(author);
        //content check fehlt
        comment.setContent(content);
        //ist Anfrager Mitglied?
        comment.setParentComment(parentComment);
        if (parentComment != null) {
            if(parentComment.getChapter() != null) {
                parentComment.getReplies().add(comment);
            }else{
                parentComment.getParentComment().getReplies().add(comment);
            }
        }else{
            comment.setChapter(chapter);
        }
        Comment createdComment=commentRepository.save(comment);
        if (parentComment == null){
            chapterService.addComment(chapter, comment);
        }
    return createdComment;
    }

    @Override
    public Comment getCommentById(Long parentCommentId) {
        return commentRepository.findById(parentCommentId).orElse(null);
    }

    @Override
    public void deleteComment(Long commentId) {
        Optional<Comment> commentOpt = commentRepository.findById(commentId);

        if (commentOpt.isPresent()) {
            Comment comment = commentOpt.get();

            // Überprüfen, ob es ein Elternkommentar ist
            if (comment.getParentComment() != null) {
                Comment parentComment = comment.getParentComment();
                parentComment.getReplies().remove(comment);
                commentRepository.save(parentComment);
            }else{
                Chapter chapter = comment.getChapter();
                if (chapter != null) {
                    chapter.getComments().remove(comment);
                    chapterRepository.save(chapter); // Kapitel speichern, um die Änderung zu persistieren
                }
            }

            // Löscht den Kommentar und seine Antworten
            commentRepository.delete(comment);
        } else {
            throw new EntityNotFoundException("Comment not found with id " + commentId);
        }
    }
}
