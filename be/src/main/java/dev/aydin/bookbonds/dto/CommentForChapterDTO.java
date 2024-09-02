package dev.aydin.bookbonds.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.Set;

@Data
public class CommentForChapterDTO {

    private Long commentId;
    private UserForMemberDTO user;
    private String content;
    private LocalDateTime createdAt ;
    private Set<CommentForChapterDTO> replies;
    private Long chapterId;

}
