package dev.aydin.bookbonds.dto;

import dev.aydin.bookbonds.entities.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserFlatDTO {
    private long user_id;
    private String username;
    private String firstname;
    private String lastname;
    private String email;
    private Role role;
    private Set<MemberForUserDTO> memberships;

}
