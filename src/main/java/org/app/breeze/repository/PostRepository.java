package org.app.breeze.repository;

import org.app.breeze.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    @Query("select p from Post p where p.title like %:title%")
    List<Post> findByTitleContaining(@Param("title") String title);

    @Query("select p from Post p where p.userId = :userId order by p.createdAt DESC")
    List<Post> findByUserId(@Param("userId") long userId);
}
