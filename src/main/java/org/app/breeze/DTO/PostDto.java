package org.app.breeze.DTO;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.Getter;
import lombok.Setter;
import org.app.breeze.View;
import org.app.breeze.enums.ContentType;

import java.time.LocalDateTime;

@Setter
@Getter
public class PostDto {
    @JsonView(View.Public.class)
    private Long id;

    @JsonView(View.Public.class)
    private UserDTO user;

    @JsonView({View.Public.class, View.Create.class})
    private String title;

    @JsonView({View.Public.class, View.Create.class})
    private String imagePath;

    @JsonView({View.Public.class, View.Create.class})
    private String content;

    @JsonView({View.Public.class, View.Create.class})
    private ContentType contentType;

    @JsonView(View.Public.class)
    private Long commentsCount;

    @JsonView(View.Public.class)
    private Long likesCount;

    @JsonView(View.Public.class)
    private LocalDateTime createdAt;

    @JsonView(View.Public.class)
    private boolean liked;
}
