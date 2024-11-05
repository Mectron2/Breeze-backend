package org.app.breeze.DTO;

import lombok.Getter;
import lombok.Setter;
import org.app.breeze.entity.User;

@Setter
@Getter
public class PostDto {
    private Long userId;
    private String title;
    private String imagePath;
    private String content;
    private String contentType;
}
