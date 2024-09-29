package com.accountability_notification_system.accountability_notification_system.services;

import java.util.Map;
import java.util.HashMap;

import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
public class UserDetailService {

    public Map<String, String> getUserDetails(OAuth2User oAuth2User) {
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
