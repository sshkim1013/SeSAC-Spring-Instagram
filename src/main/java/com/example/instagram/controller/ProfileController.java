package com.example.instagram.controller;

import com.example.instagram.dto.request.ProfileUpdateRequest;
import com.example.instagram.dto.response.UserResponse;
import com.example.instagram.security.CustomUserDetails;
import com.example.instagram.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/profile")
@RequiredArgsConstructor
public class ProfileController {

    private final UserService userService;

    @GetMapping("/edit")
    public String editForm(
        Model model,
        @AuthenticationPrincipal CustomUserDetails userDetails
    ) {
        UserResponse currentUser = userService.getUserById(userDetails.getId());

        ProfileUpdateRequest profileUpdateRequest = new ProfileUpdateRequest();
        profileUpdateRequest.setName(currentUser.getName());
        profileUpdateRequest.setBio(currentUser.getBio());

        model.addAttribute("profileUpdateRequest", profileUpdateRequest);
        model.addAttribute("currentUser", currentUser);
        return "profile/edit";
    }

}
