package org.app.breeze.service;

import org.app.breeze.entity.PostEntity;
import org.app.breeze.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {

    private final PostRepository postRepository;

    @Autowired
    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public List<PostEntity> getAllPosts() {
        return postRepository.findAll();
    }

    public PostEntity savePost(PostEntity post) {
        return postRepository.save(post);
    }

    public List<PostEntity> findPostsByTitle(String title) {
        return postRepository.findByTitleContaining(title);
    }
}
