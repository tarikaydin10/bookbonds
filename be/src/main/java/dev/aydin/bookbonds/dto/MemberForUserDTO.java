package dev.aydin.bookbonds.dto;

import dev.aydin.bookbonds.entities.ClubRole;
import lombok.Data;

@Data
public class MemberForUserDTO {
    private Long memberId;
    private ClubRole role;
private ClubForMemberDTO club;
}
