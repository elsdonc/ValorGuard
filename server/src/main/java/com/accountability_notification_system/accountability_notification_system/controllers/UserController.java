package com.accountability_notification_system.accountability_notification_system.controllers;

import java.util.Map;
import java.util.HashMap;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class UserController {
    @GetMapping("/user")
    public Map<String, String> getUser(@AuthenticationPrincipal OAuth2User oAuth2User) {
        Map<String, String> map = new HashMap<>();
        if (oAuth2User != null) {
            String name = oAuth2User.getAttribute("name");
            String email = oAuth2User.getAttribute("email");
            map.put("name", name);
            map.put("email", email);
        } else {
            map.put("error", "User not authenticated");
        }
        return map;
    }
}
