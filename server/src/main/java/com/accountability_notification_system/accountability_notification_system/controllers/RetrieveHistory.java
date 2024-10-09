package com.accountability_notification_system.accountability_notification_system.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.accountability_notification_system.accountability_notification_system.model.User;
import com.accountability_notification_system.accountability_notification_system.repositories.UserRepository;
import com.accountability_notification_system.accountability_notification_system.services.NotificationService;
import com.accountability_notification_system.accountability_notification_system.services.ValorantMatchHistoryScraperService;

@RestController
@RequestMapping("/api")
public class RetrieveHistory {

    @Autowired
    private NotificationService notificationService;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/history")
    public boolean getHistory(@AuthenticationPrincipal OAuth2User oAuth2User) throws Exception {
        User user = userRepository.findByEmail(oAuth2User.getAttribute("email"));
        boolean played = ValorantMatchHistoryScraperService.checkHistory(user);
        System.out.println(played);
        if (played) {
            notificationService.notify();
        }
        return played;
    }
}
