package org.app.breeze.repository;

import org.app.breeze.entity.PostLike;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PostLikesRepository extends JpaRepository<PostLike, Long> {
    Long countByPostId(Long postId);

    @Query("SELECT COUNT(pl) > 0 FROM PostLike pl WHERE pl.postId = :postId AND pl.userId = :userId")
    boolean existsByUserIdAndPostId(@Param("userId") long userId, @Param("postId") long postId);

    boolean existsByPostIdAndUserId(Long postId, Long userId);

    Long countByPostId(long postId);
}
