package com.example.instagram.dto.response;

import com.example.instagram.entity.Post;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PostResponse {

    // Post 내부의 정보
    private Long id;
    private String content;
    private LocalDateTime createdAt;
    private String imageUrl;

    // User 내부의 정보
    private Long userId;
    private String username;

    private long commentCount;
    private long likeCount;

    // Entity => Dto 변환
    public static PostResponse from(Post post) {
        return PostResponse.builder()
                .id(post.getId())
                .content(post.getContent())
                .createdAt(post.getCreatedAt())
                .imageUrl(post.getImageUrl())
                .userId(post.getUser().getId())
                .username(post.getUser().getUsername())
                .likeCount(0)
                .commentCount(0)
                .build();
    }

    // from 오버로딩
    public static PostResponse from(Post post, long commentCount, long likeCount) {
        return PostResponse.builder()
            .id(post.getId())
            .content(post.getContent())
            .createdAt(post.getCreatedAt())
            .imageUrl(post.getImageUrl())
            .userId(post.getUser().getId())
            .username(post.getUser().getUsername())
            .likeCount(likeCount)
            .commentCount(commentCount)
            .build();
    }

}
