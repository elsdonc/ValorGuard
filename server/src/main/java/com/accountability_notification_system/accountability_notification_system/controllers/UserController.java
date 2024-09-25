package com.accountability_notification_system.accountability_notification_system.controllers;

import java.util.Map;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.accountability_notification_system.accountability_notification_system.model.User;
import com.accountability_notification_system.accountability_notification_system.repositories.UserRepository;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserRepository userRepository;

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

    @PostMapping("/user/saveData")
    @ResponseStatus(HttpStatus.CREATED)
    public Map<String, String> saveUserData(@AuthenticationPrincipal OAuth2User oAuth2User, @RequestBody Map<String, String> json) {
        User user = userRepository.findByEmail(oAuth2User.getAttribute("email"));
        Map<String, String> map = new HashMap<>();
        if (user != null) {
            if (user.getValUser() != json.get("valUser")) {
                user.setValUser(json.get("valUser"));
            }
            if (user.getValTag() != json.get("valTag")) {
                user.setValTag(json.get("valTag"));
            }
            if (user.getNumber() != json.get("number")) {
                user.setNumber(json.get("number"));
            }
            if (user.getAccPartnerNumber() != json.get("accPartnerNumber")) {
                user.setAccPartnerNumber(json.get("accPartnerNumber"));
            }
            // TODO: set match count using scraping service
            map.put("status", "success");
        } else {
            map.put("status", "error");
        }
        return map;
    }
}
