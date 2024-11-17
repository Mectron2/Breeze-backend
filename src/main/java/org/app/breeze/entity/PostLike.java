package org.app.breeze.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.app.breeze.entity.IdClasses.PostLikeId;

@Getter
@Setter
@Entity
@Table(name = "post_likes")
@IdClass(PostLikeId.class)
@AllArgsConstructor
@NoArgsConstructor
public class PostLike {
    @Id
    @Column(name = "user_id")
    private Long userId;

    @Id
    @Column(name = "post_id")
    private Long postId;
}
