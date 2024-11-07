package org.app.breeze.controller;

import lombok.AllArgsConstructor;
import org.app.breeze.entity.Post;
import org.app.breeze.entity.PostLike;
import org.app.breeze.entity.User;
import org.app.breeze.repository.UserRepository;
import org.app.breeze.service.LikeService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController("/api/post/")
@AllArgsConstructor
public class LikesController {
    private final UserRepository userRepository;
    private LikeService likeService;

    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @PostMapping("/like")
    public ResponseEntity<?> likePost(@AuthenticationPrincipal org.springframework.security.core.userdetails.User user, @RequestParam long postId) {
        likeService.likePost(new PostLike(userRepository.getIdByUsername(user.getUsername()), postId));
        return ResponseEntity.ok().build();
    }

    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @PostMapping("/unlike")
    public ResponseEntity<?> unlikePost(@AuthenticationPrincipal org.springframework.security.core.userdetails.User user, @RequestParam long postId) {
        likeService.unlikePost(new PostLike(userRepository.getIdByUsername(user.getUsername()), postId));
        return ResponseEntity.ok().build();
    }
}
