package com.example.instagram.controller;

import com.example.instagram.dto.request.SignUpRequest;
import com.example.instagram.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;

    // 사용자 데이터를 입력 받도록 비어있는 로그인용 종이 출력
    @GetMapping("/login")
    public String login() {
        return "auth/login";
    }

    // 사용자 데이터를 입력 받도록 비어있는 회원가입용 종이 출력
    @GetMapping("/signup")
    public String signupForm(Model model) {
        model.addAttribute("signUpRequest", new SignUpRequest());
        return "auth/signup";
    }

    // 사용자 데이터를 받아서 DB에 저장하는 기능
    @PostMapping("/signup")
    public String signup(
        @Valid @ModelAttribute SignUpRequest signUpRequest,
        BindingResult bindingResult
    ) {
        if (bindingResult.hasErrors()) {
            return "auth/signup";
        }

        userService.register(signUpRequest);

        return "redirect:/auth/login";
    }

}
