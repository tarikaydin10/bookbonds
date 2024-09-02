package dev.aydin.bookbonds.controller;

import dev.aydin.bookbonds.dto.DTOMapper;
import dev.aydin.bookbonds.entities.Chapter;
import dev.aydin.bookbonds.entities.User;
import dev.aydin.bookbonds.services.ChapterService;
import dev.aydin.bookbonds.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/chapters")
@RequiredArgsConstructor
public class ChapterController {

    private final UserService userService;
    private final ChapterService chapterService;

    private final DTOMapper dtoMapper;

    @PostMapping("/{chapterId}/react")
    public ResponseEntity<?> reactToChapter(
            @RequestHeader("Authorization") String authorizationHeader,
            @PathVariable Long chapterId,
            @RequestBody Map<String, String> reaction) {
        User user = userService.getUserByHeader(authorizationHeader);
        Chapter chapter = chapterService.getChapterById(chapterId);
        String reactionType = reaction.get("type");

        if ("read".equals(reactionType)) {
            chapterService.toggleAsRead(chapter, user);

        } else if ("favorite".equals(reactionType)) {
            chapterService.toggleAsFavorite(chapter, user);
        }

        return ResponseEntity.ok().build();
    }


}
