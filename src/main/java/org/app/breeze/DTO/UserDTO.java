package org.app.breeze.DTO;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
public class UserDTO {
    private Long id;
    private String username;
    private String bio;
    private String profileImagePath;
    private LocalDateTime createdAt;
}
