package dev.aydin.bookbonds.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class UserForMemberDTO {
    private long user_id;
    private String username;
}
