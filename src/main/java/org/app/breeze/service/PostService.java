package org.app.breeze.service;

import org.app.breeze.DTO.CommentDTO;
import org.app.breeze.DTO.PostDto;
import org.app.breeze.entity.Comment;
import org.app.breeze.entity.Post;
import org.app.breeze.enums.ContentType;
import org.app.breeze.repository.CommentRepository;
import org.app.breeze.repository.PostLikesRepository;
import org.app.breeze.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostService {

    private final PostRepository postRepository;
    private final CommentService commentService;
    private final PostLikesRepository postLikesRepository;

    @Autowired
    public PostService(PostRepository postRepository, CommentService commentService, PostLikesRepository postLikesRepository) {
        this.postRepository = postRepository;
        this.commentService = commentService;
        this.postLikesRepository = postLikesRepository;
    }

    public List<PostDto> getAllPosts() {
        List<Post> posts = postRepository.findAll(Sort.by(Sort.Direction.DESC, "createdAt"));

        return posts.stream()
                .map(this::convertToPostDto)
                .collect(Collectors.toList());
    }

    public PostDto convertToPostDto(Post post) {
        PostDto postDto = new PostDto();
        postDto.setId(post.getId());
        postDto.setUserId(post.getUserId());
        postDto.setContent(post.getContent());
        postDto.setTitle(post.getTitle());
        postDto.setImagePath(post.getImagePath());
        postDto.setContentType(post.getContentType());
        postDto.setCreatedAt(post.getCreatedAt());

        List<CommentDTO> commentDTOS = commentService.getCommentsForPost(post.getId());
        postDto.setComments(commentDTOS);
        postDto.setCommentsCount((long) commentDTOS.size());

        postDto.setLikesCount(postLikesRepository.countByPostId(post.getId()));

        return postDto;
    }

    public Post savePost(@RequestBody PostDto postDto) {
        Post postEntity = new Post(
                postDto.getUserId(),
                postDto.getTitle(),
                postDto.getImagePath(),
                postDto.getContent(),
                postDto.getContentType()
        );
        return postRepository.save(postEntity);
    }

    public List<PostDto> findPostsByTitle(String title) {
        List<Post> posts = postRepository.findByTitleContaining(title);
        return posts.stream()
                .map(this::convertToPostDto)
                .collect(Collectors.toList());
    }
}
