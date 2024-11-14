package org.app.breeze.DTO;

import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;
import org.app.breeze.View;
import org.app.breeze.entity.User;

@Setter
@Getter
public class CommentDTO {
    @JsonView(View.Create.class)
    private Long postId;

    @JsonView(View.Public.class)
    private UserDTO user;

    @JsonView({View.Public.class, View.Create.class})
    private String content;
}
