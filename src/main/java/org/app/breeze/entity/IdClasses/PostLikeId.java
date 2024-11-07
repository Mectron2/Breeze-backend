package org.app.breeze.entity.IdClasses;

import java.io.Serializable;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
public class PostLikeId implements Serializable {
    private Long userId;
    private Long postId;
}
