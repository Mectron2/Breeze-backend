package org.app.breeze.service;

import org.app.breeze.DTO.PostDto;
import org.app.breeze.entity.PostEntity;
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

    public List<PostEntity> getAllPosts() {
        return postRepository.findAll(Sort.by(Sort.Direction.DESC, "createdAt"));
    }

    public PostEntity savePost(@RequestBody PostDto postDto) {
        PostEntity postEntity = new PostEntity(
                postDto.getUserId(),
                postDto.getTitle(),
                postDto.getImageUrl(),
                postDto.getContent(),
                ContentType.valueOf(postDto.getContentType().toUpperCase())
        );
        return postRepository.save(postEntity);
    }

    public List<PostEntity> findPostsByTitle(String title) {
        return postRepository.findByTitleContaining(title);
    }
}
