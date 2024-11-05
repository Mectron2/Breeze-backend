package org.app.breeze.repository;

import java.util.List;
import org.app.breeze.entity.Comment;
import org.app.breeze.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    @Query("select cm from Comment cm where cm.post.id = :postId")
    public List<Comment> getCommentsOnPost(Long postId);
}
