package org.app.breeze.service;

import org.app.breeze.DTO.CommentDTO;
import org.app.breeze.DTO.UserDTO;
import org.app.breeze.entity.Comment;
import org.app.breeze.entity.Post;
import org.app.breeze.entity.User;
import org.app.breeze.repository.CommentRepository;
import org.app.breeze.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentService {
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final UserService userService;

    @Autowired
    public CommentService(CommentRepository commentRepository, UserRepository userRepository, UserService userService) {
        this.commentRepository = commentRepository;
        this.userRepository = userRepository;
        this.userService = userService;
    }

    public Comment addComment(Comment comment) {
        return commentRepository.save(comment);
    }

    public List<CommentDTO> getCommentsForPost(Long postId) {
        List<Comment> comments = commentRepository.getCommentsOnPost(postId);
        return comments.stream()
                .map(comment -> {
                    CommentDTO dto = new CommentDTO();
                    User user = userRepository.findById(comment.getUserId()).get();
                    dto.setUser(userService.convertToUserDto(user));
                    dto.setContent(comment.getContent());
                    dto.setPostId(comment.getPost().getId());
                    dto.setId(comment.getId());
                    return dto;
                })
                .collect(Collectors.toList());
    }
}
