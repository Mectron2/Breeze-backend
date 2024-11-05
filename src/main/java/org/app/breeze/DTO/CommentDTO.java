package org.app.breeze.DTO;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CommentDTO {
    private Long postId;
    private Long userId;
    private String content;
}
