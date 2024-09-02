package dev.aydin.bookbonds.dto;

import lombok.Data;

@Data
public class CreateJourneyDTO {
    private Long journeyId;
    private String title;
    private String description;
    private String bookId;
    private Long clubId;
    private int numberOfChapters;
}
