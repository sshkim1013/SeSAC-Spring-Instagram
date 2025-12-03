package com.example.instagram.dto.response;

import com.example.instagram.entity.User;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ProfileResponse {

    private Long id;
    private String username;
    private String bio;
    private String name;

    // 통계
    private long postCount;
    private long followerCount;
    private long followingCount;

    // Entity => Dto
    public static ProfileResponse from(User user) {
        return ProfileResponse.builder()
            .username(user.getUsername())
            .bio(user.getBio())
            .name(user.getName())
            .postCount(0)
            .followerCount(0)
            .followingCount(0)
            .build();
    }

}
