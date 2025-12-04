package com.example.instagram.controller;

import com.example.instagram.dto.response.PostResponse;
import com.example.instagram.dto.response.ProfileResponse;
import com.example.instagram.security.CustomUserDetails;
import com.example.instagram.service.FollowService;
import com.example.instagram.service.PostService;
import com.example.instagram.service.UserService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final PostService postService;
    private final FollowService followService;

    @GetMapping("/{username}")
    public String profile(
        @PathVariable String username,
        Model model,
        @AuthenticationPrincipal CustomUserDetails userDetails
    ) {
        ProfileResponse profile = userService.getProfile(username);
        List<PostResponse> posts = postService.getPostsByUsername(username);

        model.addAttribute("profile", profile);
        model.addAttribute("posts", posts);

        boolean isFollowing = followService.isFollowing(userDetails.getId(), profile.getId());
        model.addAttribute("isFollowing", isFollowing);

        // userDetails.getUsername() : 현재 로그인한 유저의 이름
        boolean isOwner = userDetails.getUsername().equals(username);
        model.addAttribute("isOwner", isOwner);

        return "user/profile";
    }

    @PostMapping("/{username}/follow")
    public String toggleFollow(
        @PathVariable String username,
        @AuthenticationPrincipal CustomUserDetails userDetails
    ) {
        // .toggleFollow(유저 본인, 내가 팔로우 하고 싶은 사람)
        // username을 통해 해당 유저의 id를 찾는 방법을 사용할 수도 있다!
        followService.toggleFollow(userDetails.getId(), username);
        return "redirect:/users/" + username;
    }

}
