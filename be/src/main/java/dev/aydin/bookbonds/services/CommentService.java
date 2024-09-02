package dev.aydin.bookbonds.services;

import dev.aydin.bookbonds.entities.Chapter;
import dev.aydin.bookbonds.entities.Comment;
import dev.aydin.bookbonds.entities.User;


public interface CommentService {


    Comment createComment(String content, User author, Chapter chapter, Comment parentComment);

    Comment getCommentById(Long parentCommentId);

    void deleteComment(Long commentId);
}
