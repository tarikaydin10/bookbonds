package dev.aydin.bookbonds.dto;

import lombok.Data;

@Data
public class UserReactionDTO {

    private Long reactionId;

    private UserForMemberDTO user;

    private boolean isRead;
    private boolean isFavorited;

}
