package org.app.breeze.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterDTO {
    private String username;
    private String password;
    private String email;
    private String bio;
    private String profileImagePath;
}