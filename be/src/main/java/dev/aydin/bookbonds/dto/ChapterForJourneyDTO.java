package dev.aydin.bookbonds.dto;

import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class ChapterForJourneyDTO {

    private Long chapterId;

    private String title;

    private int chapterNumber;

    private java.util.Set<CommentForChapterDTO> comments = new HashSet<>();

    private Set<UserReactionDTO> reactions = new HashSet<>();

    private double chapterProgress;

    private Long clubId;

}
