package com.example.instagram.controller;

import com.example.instagram.dto.response.PostResponse;
import com.example.instagram.security.CustomUserDetails;
import com.example.instagram.service.BookmarkService;
import com.example.instagram.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class PostApiController {

    private final PostService postService;
    private final BookmarkService bookmarkService;

    @GetMapping("/feed")
    public Slice<PostResponse> getFeed(
        @AuthenticationPrincipal CustomUserDetails userDetails,
        @PageableDefault(size = 5)Pageable pageable
    ) {
        return postService.getFeedPosts(userDetails.getId(), pageable);
    }

    // @PageableDefault : size=? 과 같은 데이터를 받을 수 있게 해준다.
    @GetMapping("/explore")
    public Slice<PostResponse> getExplore(
        @PageableDefault(size = 12) Pageable pageable
    ) {
        return postService.getAllPostsPaging(pageable);
    }

    @PostMapping("/posts/{id}/bookmark")
    @ResponseStatus(HttpStatus.OK)
    public void toggleBookmark(
        @PathVariable Long id,
        @AuthenticationPrincipal CustomUserDetails userDetails
    ) {
        bookmarkService.toggleBookmark(id, userDetails.getId());
    }
}
