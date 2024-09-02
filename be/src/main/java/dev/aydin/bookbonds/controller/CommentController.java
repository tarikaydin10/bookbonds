package dev.aydin.bookbonds.controller;

import dev.aydin.bookbonds.entities.*;
import dev.aydin.bookbonds.services.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/comments")
@RequiredArgsConstructor
public class CommentController {
    private final UserService userService;
    private final ClubMemberService clubMemberService;
    private final ChapterService chapterService;
    private final ClubService clubService;
    private final CommentService commentService;

    @PostMapping("/club/{clubId}/chapter/{chapterId}")
    public ResponseEntity<Comment> createComment(@RequestHeader("Authorization") String authorizationHeader,
                                                 @PathVariable Long clubId,
                                                 @PathVariable Long chapterId,
                                                 @RequestBody Map<String, String> body) {
        User author = userService.getUserByHeader(authorizationHeader);
        Club club = clubService.getClubById(clubId);
        ClubRole role = clubMemberService.getUserRoleInClub(author.getUser_id(), club.getClubId());
        if(role == null){
            return ResponseEntity.badRequest().build();
        }
        String content = body.get("content");

        System.out.println( body.get("parentCommentId"));
        Long parentCommentId = body.get("parentCommentId") != null ? Long.parseLong(body.get("parentCommentId")) : null;

        Comment parentComment = (parentCommentId != null) ? commentService.getCommentById(parentCommentId) : null;

        Chapter chapter = chapterService.getChapterById(chapterId);
        Comment createdComment = commentService.createComment(content, author, chapter, parentComment);
        return ResponseEntity.ok(createdComment);


    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity<Void> deleteComment(@PathVariable Long commentId) {
        System.out.println(commentId);
        commentService.deleteComment(commentId);
        return ResponseEntity.noContent().build(); // Rückgabe von 204 No Content
    }



}
