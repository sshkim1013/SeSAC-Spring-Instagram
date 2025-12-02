package com.example.instagram.service;

import com.example.instagram.dto.request.PostCreateRequest;
import com.example.instagram.dto.response.PostResponse;
import com.example.instagram.entity.Post;

public interface PostService {

    PostResponse create(PostCreateRequest postCreateRequest, Long userId);

    PostResponse getPost(Long postId);

    Post findById(Long postId);

}
