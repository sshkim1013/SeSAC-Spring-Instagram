package com.example.instagram.service;

public interface FollowService {

    void toggleFollow(Long followingId, String followerUsername);

    boolean isFollowing(Long followerId, Long followingId);

    long countByFollowerId(Long followerId);

    long countByFollowingId(Long followingId);

}
