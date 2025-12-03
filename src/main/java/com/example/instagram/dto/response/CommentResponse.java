package com.example.instagram.dto.response;

import com.example.instagram.entity.Comment;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CommentResponse {

    // 댓글 정보
    private Long id;
    private String content;
    private LocalDateTime createdAt;

    // 작성자 정보
    private Long userId;
    private String username;

    public static CommentResponse from(Comment comment) {
        return CommentResponse.builder()
            .id(comment.getId())
            .content(comment.getContent())
            .createdAt(comment.getCreatedAt())
            .userId(comment.getUser().getId())
            .username(comment.getUser().getUsername())
            .build();
    }

}
