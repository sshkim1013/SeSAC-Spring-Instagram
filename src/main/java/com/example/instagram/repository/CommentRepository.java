package com.example.instagram.repository;

import com.example.instagram.entity.Comment;
import java.util.List;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    @EntityGraph(attributePaths = {"user"})
    List<Comment> findByPostIdOrderByCreatedAtDesc(Long postId);

    long countByPostId(Long id);

}
