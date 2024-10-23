package com.accountability_notification_system.accountability_notification_system.services;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;

@Service
public class NotificationService {

    @Autowired
    private UserDetailService userDetailService;

    public void notify(@AuthenticationPrincipal OAuth2User oAuth2User, @Value("${twilio.accountSid}") String accountSid, @Value("${twilio.authToken}") String authToken,
            @Value("${twilio.phoneNumber}") String phoneNumber) {

        Map<String, String> userDetails = userDetailService.getUserDetails(oAuth2User);
        Twilio.init(accountSid, authToken);
        Message message = Message.creator(
                new com.twilio.type.PhoneNumber(phoneNumber),
                new com.twilio.type.PhoneNumber(phoneNumber),
                String.format("%s played a game.", userDetails.get("name")))
                .create();
        System.out.println(message.getBody());
    }
}