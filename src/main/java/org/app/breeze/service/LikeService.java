package org.app.breeze.service;

import lombok.AllArgsConstructor;
import org.app.breeze.entity.PostLike;
import org.app.breeze.repository.PostLikesRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.app.breeze.exception.AlreadyLikedException;
import org.app.breeze.exception.NotLikedException;

@AllArgsConstructor
@Service
public class LikeService {
    private final PostLikesRepository postLikesRepository;

    @Transactional
    public void likePost(PostLike postLike) {
        if (postLikesRepository.existsByUserIdAndPostId(postLike.getUserId(), postLike.getPostId())) {
            throw new AlreadyLikedException("This post is already liked by the user.");
        }
        postLikesRepository.save(postLike);
    }

    @Transactional
    public void unlikePost(PostLike postLike) {
        if (!postLikesRepository.existsByUserIdAndPostId(postLike.getUserId(), postLike.getPostId())) {
            throw new NotLikedException("Cannot unlike a post that hasn't been liked.");
        }
        postLikesRepository.delete(postLike);
    }

    public boolean isLiked(Long postId, Long userId) {
        return postLikesRepository.existsByPostIdAndUserId(postId, userId);
    }
}
