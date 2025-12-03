package com.example.instagram.controller;

import com.example.instagram.dto.request.CommentRequest;
import com.example.instagram.dto.request.PostCreateRequest;
import com.example.instagram.dto.response.CommentResponse;
import com.example.instagram.dto.response.PostResponse;
import com.example.instagram.security.CustomUserDetails;
import com.example.instagram.service.CommentService;
import com.example.instagram.service.LikeService;
import com.example.instagram.service.PostService;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/posts")
public class PostController {

    private final PostService postService;
    private final CommentService commentService;
    private final LikeService likeService;

    @GetMapping("/new")
    public String createForm(Model model) {
        model.addAttribute("postCreateRequest", new PostCreateRequest());
        return "post/form";
    }

    @PostMapping
    public String create(
        @Valid @ModelAttribute PostCreateRequest postCreateRequest,
        BindingResult bindingResult,
        @AuthenticationPrincipal CustomUserDetails userDetails
    ) {
        // postCreateRequest 클래스의 content 필드에
        // 0자 또는 1000자 이상이 들어간 경우
        if (bindingResult.hasErrors()) {
            return "post/form";
        }

        // UserDetails 객체에서 user의 id를 가져 온다.
        postService.create(postCreateRequest, userDetails.getId());

        return "redirect:/";
    }

    @GetMapping("/{id}")
    public String detail(
        @PathVariable Long id,
        Model model
    ) {
        PostResponse post = postService.getPost(id);

        List<CommentResponse> comments = commentService.getComments(id);

        model.addAttribute("post", post);
        model.addAttribute("commentRequest", new CommentRequest());
        model.addAttribute("comments", comments);

        return "post/detail";
    }

    @PostMapping("/{postId}/comments")
    public String createComment(
        @PathVariable Long postId,
        @Valid @ModelAttribute CommentRequest commentRequest,
        BindingResult bindingResult,
        @AuthenticationPrincipal CustomUserDetails userDetails,
        Model model
    ) {
        if (bindingResult.hasErrors()) {    // 댓글 길이가 500자를 초과한다면
            PostResponse post = postService.getPost(postId);
            List<CommentResponse> comments = commentService.getComments(postId);

            model.addAttribute("post", post);
            model.addAttribute("comments", comments);
//            model.addAttribute("commentRequest", commentRequest);

            return "post/detail";
        }

        // postId: 어떤 게시글에 작성이 되었는지
        // commentCreateRequest: 어떤 댓글 작성 요청이 들어왔는지
        // userDetails.getId(): 어떤 유저가 작성하는지
        commentService.create(postId, commentRequest, userDetails.getId());

        return "redirect:/posts/" + postId;
    }

    @PostMapping("/{id}/like")
    public String toggleLike(   // 좋아요 버튼 클릭 시, 좋아요 실행 또는 취소 상태
        @PathVariable Long id,
        @AuthenticationPrincipal CustomUserDetails userDetails
    ) {
        // 게시글 ID, 유저 ID
        likeService.toggleLike(id, userDetails.getId());
        return "redirect:/posts/" + id;
    }

}
