package com.example.instagram.service;

import com.example.instagram.dto.request.PostCreateRequest;
import com.example.instagram.dto.response.PostResponse;
import com.example.instagram.entity.Post;
import java.util.List;
import org.springframework.web.multipart.MultipartFile;

public interface PostService {

    PostResponse create(PostCreateRequest postCreateRequest, MultipartFile image, Long userId);

    PostResponse getPost(Long postId);

    Post findById(Long postId);

    List<PostResponse> getAllPosts();

    List<PostResponse> getPostsByUsername(String username);

    long countByUserId(Long userId);

    List<PostResponse> getAllPostsWithStats();

}
