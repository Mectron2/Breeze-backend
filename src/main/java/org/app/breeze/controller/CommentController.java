package org.app.breeze.controller;

import org.app.breeze.DTO.CommentDTO;
import org.app.breeze.entity.Comment;
import org.app.breeze.repository.PostRepository;
import org.app.breeze.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/post/comments/{postId}")
public class CommentController {
    @Autowired
    private CommentService commentService;
    @Autowired
    private PostRepository postRepository;

    @GetMapping()
    public ResponseEntity<List<CommentDTO>> getCommentsForPost(@PathVariable int postId) {
        List<CommentDTO> comments = commentService.getCommentsForPost(postId);
        return ResponseEntity.ok(comments);
    }

    @PostMapping
    public ResponseEntity<Comment> addComment(@RequestBody CommentDTO commentDTO) {
        Comment comment = new Comment();
        comment.setPost(postRepository.findById(commentDTO.getPostId()).get());
        comment.setUserId(commentDTO.getUserId());
        comment.setContent(commentDTO.getContent());
        Comment savedComment = commentService.addComment(comment);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedComment);
    }
}
