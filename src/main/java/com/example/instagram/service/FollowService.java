package com.example.instagram.service;

public interface FollowService {

    void toggleFollow(Long followingId, String followerUsername);

}
