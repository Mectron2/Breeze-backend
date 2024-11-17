package org.app.breeze.DTO;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.app.breeze.View;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CommentDTO {
    @JsonView(View.Public.class)
    private Long id;

    @JsonView(View.Create.class)
    private Long postId;

    @JsonView(View.Public.class)
    private UserDTO user;

    @JsonView({View.Public.class, View.Create.class})
    private String content;
}
