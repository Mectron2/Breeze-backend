package org.app.breeze.controller;

import com.fasterxml.jackson.annotation.JsonView;
import org.app.breeze.DTO.PostDto;
import org.app.breeze.View;
import org.app.breeze.entity.Post;
import org.app.breeze.exception.ResourceNotFoundException;
import org.app.breeze.repository.PostRepository;
import org.app.breeze.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    private final PostService postService;
    private final PostRepository postRepository;

    @Autowired
    public PostController(PostService postService, PostRepository postRepository) {
        this.postService = postService;
        this.postRepository = postRepository;
    }

    @GetMapping
    public List<PostDto> getAllPosts() {
        return postService.getAllPosts();
    }

    @PostMapping
    public ResponseEntity<Post> createPost(@RequestBody @JsonView(View.Create.class) PostDto postDto) {
        Post savedPost = postService.savePost(postDto);
        return new ResponseEntity<>(savedPost, HttpStatus.CREATED);
    }

    @GetMapping("/search")
    public List<PostDto> findPostsByTitle(@RequestParam String title) {
        return postService.findPostsByTitle(title);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/getById")
    public ResponseEntity<PostDto> getPostById(@RequestParam("postId") Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Post not found."));
        PostDto postDto = postService.convertToPostDto(post);
        return ResponseEntity.ok(postDto);
    }
}
