package org.app.breeze.service;

import org.app.breeze.DTO.CommentDTO;
import org.app.breeze.entity.Comment;
import org.app.breeze.entity.Post;
import org.app.breeze.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentService {
    @Autowired
    private CommentRepository commentRepository;

    public Comment addComment(Comment comment) {
        return commentRepository.save(comment);
    }

    public List<CommentDTO> getCommentsForPost(int postId) {
        List<Comment> comments = commentRepository.getCommentsOnPost(postId);
        return comments.stream()
                .map(comment -> {
                    CommentDTO dto = new CommentDTO();
                    dto.setUserId(comment.getUserId());
                    dto.setContent(comment.getContent());
                    dto.setPostId(comment.getPost().getId());
                    return dto;
                })
                .collect(Collectors.toList());
    }
}
