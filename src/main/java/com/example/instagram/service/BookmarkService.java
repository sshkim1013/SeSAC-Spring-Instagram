package com.example.instagram.service;

public interface BookmarkService {

    void toggleBookmark(Long postId, Long userId);

    boolean isBookmarked(Long postId, Long userId);

    long getBookmarkCount(Long postId);

}
