package com.example.instagram.service;

import com.example.instagram.entity.Bookmark;
import com.example.instagram.entity.Post;
import com.example.instagram.entity.User;
import com.example.instagram.exception.BusinessException;
import com.example.instagram.exception.ErrorCode;
import com.example.instagram.repository.BookmarkRepository;
import com.example.instagram.repository.PostRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BookmarkServiceImpl implements BookmarkService {

    private final BookmarkRepository bookmarkRepository;
    private final PostRepository postRepository;
    private final UserService userService;

    @Transactional
    @Override
    public void toggleBookmark(Long postId, Long userId) {
        Optional<Bookmark> existingBookmark = bookmarkRepository.findByPostIdAndUserId(postId, userId);

        if (existingBookmark.isPresent()) {
            bookmarkRepository.delete(existingBookmark.get());
        } else {
            Post post = postRepository.findById(postId)
                .orElseThrow(() -> new BusinessException(ErrorCode.POST_NOT_FOUND));
            User user = userService.findById(userId);

            Bookmark bookmark = Bookmark.builder()
                .post(post)
                .user(user)
                .build();

            bookmarkRepository.save(bookmark);
        }
    }

    @Override
    public boolean isBookmarked(Long postId, Long userId) {
        return bookmarkRepository.existsByPostIdAndUserId(postId, userId);
    }

    @Override
    public long getBookmarkCount(Long postId) {
        return bookmarkRepository.countByPostId(postId);
    }

}
