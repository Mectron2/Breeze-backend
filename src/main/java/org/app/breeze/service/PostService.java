package org.app.breeze.service;

import org.app.breeze.DTO.PostDto;
import org.app.breeze.entity.Post;
import org.app.breeze.enums.ContentType;
import org.app.breeze.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class PostService {

    private final PostRepository postRepository;

    @Autowired
    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public List<Post> getAllPosts() {
        return postRepository.findAll(Sort.by(Sort.Direction.DESC, "createdAt"));
    }

    public Post savePost(@RequestBody PostDto postDto) {
        Post postEntity = new Post(
                postDto.getUserId(),
                postDto.getTitle(),
                postDto.getImageUrl(),
                postDto.getContent(),
                ContentType.valueOf(postDto.getContentType().toUpperCase())
        );
        return postRepository.save(postEntity);
    }

    public List<Post> findPostsByTitle(String title) {
        return postRepository.findByTitleContaining(title);
    }
}
