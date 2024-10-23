package com.accountability_notification_system.accountability_notification_system.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.accountability_notification_system.accountability_notification_system.model.User;
import com.accountability_notification_system.accountability_notification_system.repositories.UserRepository;

@Service
@EnableScheduling
public class ScheduledCheckService {
    @Autowired
    private UserRepository userRepository;

    @Value("${user.email}")
    private String userEmail;
    
    @Scheduled(fixedDelay = 3600000)
    public void scheduledCheck() throws Exception {
        User user = userRepository.findByEmail(userEmail);
        ValorantMatchHistoryScraperService.checkHistory(user);
    }
}
