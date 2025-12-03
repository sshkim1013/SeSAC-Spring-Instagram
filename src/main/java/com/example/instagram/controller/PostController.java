package com.example.instagram.controller;

import com.example.instagram.dto.request.CommentCreateRequest;
import com.example.instagram.dto.request.PostCreateRequest;
import com.example.instagram.dto.response.PostResponse;
import com.example.instagram.security.CustomUserDetails;
import com.example.instagram.service.PostService;
import jakarta.validation.Valid;
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
        model.addAttribute("post", post);
        model.addAttribute("commentRequest", new CommentCreateRequest());

        return "post/detail";
    }

}
