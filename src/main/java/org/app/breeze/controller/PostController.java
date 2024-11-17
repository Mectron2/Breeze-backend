package org.app.breeze.controller;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.AllArgsConstructor;
import org.app.breeze.DTO.PostDto;
import org.app.breeze.View;
import org.app.breeze.entity.Post;
import org.app.breeze.exception.ResourceNotFoundException;
import org.app.breeze.repository.PostRepository;
import org.app.breeze.repository.UserRepository;
import org.app.breeze.service.LikeService;
import org.app.breeze.service.PostService;
import org.app.breeze.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/posts")
@AllArgsConstructor
public class PostController {

    private final PostService postService;
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final UserService userService;
    private final LikeService likeService;

    @GetMapping
    public List<PostDto> getAllPosts(@AuthenticationPrincipal User user) {
        List<PostDto> posts = postService.getAllPosts();
        if (user != null) {
            Long userId = userRepository.getIdByUsername(user.getUsername());
            for (PostDto post : posts) {
                post.setLiked(likeService.isLiked(post.getId(), userId));
            }
        } else {
            posts.forEach(post -> post.setLiked(false));
        }
        return posts;
    }

    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @JsonView(View.Create.class)
    @PostMapping
    public ResponseEntity<Post> createPost(@AuthenticationPrincipal User user, @RequestBody PostDto postDto) {
        postDto.setUser(userService.convertToUserDto(userRepository.findByUsername(user.getUsername()).get()));
        Post savedPost = postService.savePost(postDto);
        return new ResponseEntity<>(savedPost, HttpStatus.CREATED);
    }

    @GetMapping("/search")
    public List<PostDto> findPostsByTitle(@AuthenticationPrincipal User user, @RequestParam String title) {
        List<PostDto> posts = postService.findPostsByTitle(title);
        if (user != null) {
            Long userId = userRepository.getIdByUsername(user.getUsername());
            for (PostDto post : posts) {
                post.setLiked(likeService.isLiked(post.getId(), userId));
            }
        } else {
            posts.forEach(post -> post.setLiked(false));
        }
        return posts;
    }

    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @GetMapping("/getById")
    public ResponseEntity<PostDto> getPostById(@AuthenticationPrincipal User user, @RequestParam("postId") Long id) {
        Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post not found."));
        PostDto postDto = postService.convertToPostDto(post);
        postDto.setLiked(likeService.isLiked(post.getId(), userRepository.getIdByUsername(user.getUsername())));
        return ResponseEntity.ok(postDto);
    }

    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @DeleteMapping("/{postId}")
    public ResponseEntity<?> deletePostById(@AuthenticationPrincipal User user, @PathVariable Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("Post not found."));

        Long currentUserId = userRepository.getIdByUsername(user.getUsername());

        boolean isAdmin = user.getAuthorities().stream()
                .anyMatch(auth -> auth.getAuthority().equals("ROLE_ADMIN"));

        if (Objects.equals(post.getUserId(), currentUserId) || isAdmin) {
            postRepository.delete(post);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
    }

}
