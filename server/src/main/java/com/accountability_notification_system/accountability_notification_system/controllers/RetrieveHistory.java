package com.accountability_notification_system.accountability_notification_system.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.accountability_notification_system.accountability_notification_system.services.ValorantMatchHistoryScraperService;

@RestController
@RequestMapping("/api")
public class RetrieveHistory {
    @GetMapping("/history")
    public boolean getHistory() throws Exception {
        // TODO: trigger sms message to accountability partner if checkHistory() returns true
        return ValorantMatchHistoryScraperService.checkHistory();
    }
}
