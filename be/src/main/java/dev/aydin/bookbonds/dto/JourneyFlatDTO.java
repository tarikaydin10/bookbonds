package dev.aydin.bookbonds.dto;

import dev.aydin.bookbonds.entities.Book;
import lombok.Data;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Data
public class JourneyFlatDTO {
    private Long journeyId;
    private String title;
    private String description;
private Book book;
    private Set<ChapterForJourneyDTO> chapters = new HashSet<>();

    private int numberOfChapters;
    private Date createdAt;
    private double progress;
}
