package com.happymemories.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @Builder @NoArgsConstructor @AllArgsConstructor
public class UserDTO {
    private String firstname;
    private String lastname;
    private String emailUser;
    private String password;
    private String roles;
}
