package org.app.breeze.controller;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.AllArgsConstructor;
import org.app.breeze.DTO.CommentDTO;
import org.app.breeze.View;
import org.app.breeze.entity.Comment;
import org.app.breeze.entity.Post;
import org.app.breeze.exception.ResourceNotFoundException;
import org.app.breeze.repository.CommentRepository;
import org.app.breeze.repository.PostRepository;
import org.app.breeze.repository.UserRepository;
import org.app.breeze.service.CommentService;
import org.app.breeze.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/post/comments")
@AllArgsConstructor
public class CommentController {
    private final CommentRepository commentRepository;
    private final UserService userService;
    private CommentService commentService;
    private PostRepository postRepository;
    private UserRepository userRepository;

    @GetMapping("/{postId}")
    public ResponseEntity<List<CommentDTO>> getCommentsForPost(@PathVariable Long postId) {
        List<CommentDTO> comments = commentService.getCommentsForPost(postId);
        return ResponseEntity.ok(comments);
    }

    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @PostMapping
    @JsonView(View.Public.class)
    public ResponseEntity<CommentDTO> addComment(
            @AuthenticationPrincipal org.springframework.security.core.userdetails.User user,
            @JsonView(View.Create.class) @RequestBody CommentDTO commentRequestDTO) {  // применяется к запросу

        Comment comment = new Comment(
                postRepository.findById(commentRequestDTO.getPostId())
                        .orElseThrow(() -> new ResourceNotFoundException("Post not found")),
                userRepository.getIdByUsername(user.getUsername()),
                commentRequestDTO.getContent()
        );

        Comment savedComment = commentService.addComment(comment);

        CommentDTO commentDTOResponse = new CommentDTO(
                savedComment.getId(),
                savedComment.getPost().getId(),
                userService.convertToUserDto(
                        userRepository.findById(savedComment.getUserId())
                                .orElseThrow(() -> new ResourceNotFoundException("User not found"))
                ),
                savedComment.getContent()
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(commentDTOResponse);
    }


    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @DeleteMapping("/{commentId}")
    public ResponseEntity<?> deleteCommentById(@AuthenticationPrincipal User user, @PathVariable Long commentId) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new ResourceNotFoundException("Comment not found."));

        Long currentUserId = userRepository.getIdByUsername(user.getUsername());

        boolean isAdmin = user.getAuthorities().stream()
                .anyMatch(auth -> auth.getAuthority().equals("ROLE_ADMIN"));

        if (Objects.equals(comment.getUserId(), currentUserId) || isAdmin) {
            commentRepository.delete(comment);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
    }
}
