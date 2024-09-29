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

import com.accountability_notification_system.accountability_notification_system.exceptions.UserNotFoundException;
import com.accountability_notification_system.accountability_notification_system.services.UserDetailService;
import com.accountability_notification_system.accountability_notification_system.services.UserUpdateService;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserDetailService userDetailService;

    @Autowired
    private UserUpdateService userUpdateService;

    @GetMapping("/user")
    public Map<String, String> getUser(@AuthenticationPrincipal OAuth2User oAuth2User) {
        return userDetailService.getUserDetails(oAuth2User);
    }

    @PostMapping("/user/saveData")
    @ResponseStatus(HttpStatus.CREATED)
    public Map<String, String> saveUserData(@AuthenticationPrincipal OAuth2User oAuth2User, @RequestBody Map<String, String> json) {
        Map<String, String> response = new HashMap<>();
        try {
            userUpdateService.saveUserData(oAuth2User, json);
            response.put("status", "success");
        } catch (UserNotFoundException e) {
            response.put("status", "error");
            response.put("message", e.getMessage());
        } catch (Exception e) {
            response.put("status", "error");
            response.put("message", "An error occurred while saving user data");
        }
        return response;
    }
}