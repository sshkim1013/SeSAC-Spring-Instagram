package com.example.instagram.service;

import com.example.instagram.dto.request.PostCreateRequest;
import com.example.instagram.dto.response.PostResponse;
import com.example.instagram.entity.Post;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.web.multipart.MultipartFile;

public interface PostService {

    PostResponse create(PostCreateRequest postCreateRequest, MultipartFile image, Long userId);

    PostResponse getPost(Long postId);

    Post findById(Long postId);

    List<PostResponse> getAllPosts();

    List<PostResponse> getPostsByUsername(String username);

    long countByUserId(Long userId);

    List<PostResponse> getAllPostsWithStats();

    // 피드 조회(무한 스크롤)
    // 유저의 ID를 기준으로 모든 게시물 조회
    Slice<PostResponse> getFeedPosts(Long userId, Pageable pageable);

    // 전체 게시물 조회
    Slice<PostResponse> getAllPostsPaging(Pageable pageable);

    Slice<PostResponse> searchPosts(String keyword, Pageable pageable);

}
