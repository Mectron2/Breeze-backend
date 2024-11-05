package org.app.breeze.DTO;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class UserDTO {
    private Long id;
    private String username;
    private String bio;
    private String profileImagePath;
    private Date createdAt;
}
