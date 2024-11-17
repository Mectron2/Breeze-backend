package org.app.breeze.controller;

import lombok.AllArgsConstructor;
import org.app.breeze.entity.PostLike;
import org.app.breeze.repository.PostLikesRepository;
import org.app.breeze.repository.UserRepository;
import org.app.breeze.service.LikeService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class LikesController {
    private final UserRepository userRepository;
    private final PostLikesRepository postLikesRepository;
    private LikeService likeService;

    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @PostMapping("/api/post/like")
    public ResponseEntity<?> likePost(@AuthenticationPrincipal org.springframework.security.core.userdetails.User user, @RequestParam long postId) {
        likeService.likePost(new PostLike(userRepository.getIdByUsername(user.getUsername()), postId));
        return ResponseEntity.ok().build();
    }

    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @PostMapping("/api/post/unlike")
    public ResponseEntity<?> unlikePost(@AuthenticationPrincipal org.springframework.security.core.userdetails.User user, @RequestParam long postId) {
        likeService.unlikePost(new PostLike(userRepository.getIdByUsername(user.getUsername()), postId));
        return ResponseEntity.ok().build();
    }

    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @GetMapping("/api/post/isLiked")
    public ResponseEntity<?> isLiked(@AuthenticationPrincipal org.springframework.security.core.userdetails.User user, @RequestParam long postId) {
        boolean isLiked = likeService.isLiked(postId, userRepository.getIdByUsername(user.getUsername()));
        return ResponseEntity.ok(isLiked);
    }

    @GetMapping("/api/post/getLikes")
    public ResponseEntity<?> getLikes(@RequestParam Long postId) {
        return ResponseEntity.ok(postLikesRepository.countByPostId(postId));
    }
}
