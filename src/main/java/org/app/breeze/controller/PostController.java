package org.app.breeze.controller;

import org.app.breeze.entity.PostEntity;
import org.app.breeze.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping
    public List<PostEntity> getAllPosts() {
        return postService.getAllPosts();
    }

    @PostMapping
    public PostEntity createPost(@RequestBody PostEntity post) {
        return postService.savePost(post);
    }

    @GetMapping("/search")
    public List<PostEntity> findPostsByTitle(@RequestParam String title) {
        return postService.findPostsByTitle(title);
    }
}
