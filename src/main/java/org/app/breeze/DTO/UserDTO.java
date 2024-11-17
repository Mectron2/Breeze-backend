package org.app.breeze.DTO;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.Getter;
import lombok.Setter;
import org.app.breeze.View;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class UserDTO {
    @JsonView(View.Public.class)
    private Long id;
    @JsonView(View.Public.class)
    private String username;
    @JsonView(View.Public.class)
    private String bio;
    @JsonView(View.Public.class)
    private String profileImagePath;
    @JsonView(View.Public.class)
    private LocalDateTime createdAt;
    @JsonView(View.Public.class)
    private List<PostDto> postDtoList;
}
