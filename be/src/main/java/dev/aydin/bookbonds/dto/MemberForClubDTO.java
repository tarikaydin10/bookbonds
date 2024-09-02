package dev.aydin.bookbonds.dto;

import dev.aydin.bookbonds.entities.ClubRole;
import lombok.Data;

@Data
public class MemberForClubDTO {
    Long memberId;
    private UserForMemberDTO user;

    private ClubRole role;
}
