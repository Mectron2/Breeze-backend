package org.app.breeze.DTO;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PostDto {
    private String title;
    private String imageUrl;
    private String content;
}