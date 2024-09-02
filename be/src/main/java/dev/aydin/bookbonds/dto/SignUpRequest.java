package dev.aydin.bookbonds.dto;

import lombok.Data;

@Data
public class SignUpRequest {


    private String username;

    private String firstname;

    private String lastname;

    private String email ;

    private String password;

}
