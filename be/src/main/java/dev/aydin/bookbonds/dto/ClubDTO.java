package dev.aydin.bookbonds.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Data
public class ClubDTO {
    private Long clubId;
    private String title;
    private String description;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Set<MemberForClubDTO> members = new HashSet<>();
    private Set<JourneyFlatDTO> journeys = new HashSet<>();
    private String theme;

}
