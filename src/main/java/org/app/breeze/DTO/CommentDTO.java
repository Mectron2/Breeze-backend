package org.app.breeze.DTO;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.Getter;
import lombok.Setter;
import org.app.breeze.View;

@Setter
@Getter
public class CommentDTO {
    @JsonView(View.Create.class)
    private Long postId;

    @JsonView(View.Public.class)
    private Long userId;

    @JsonView({View.Public.class, View.Create.class})
    private String content;
}
