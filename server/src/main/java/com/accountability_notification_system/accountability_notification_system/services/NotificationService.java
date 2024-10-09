package com.accountability_notification_system.accountability_notification_system.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;

@Service
public class NotificationService {

    public void notify(@Value("${twilio.accountSid}") String accountSid, @Value("${twilio.authToken}") String authToken,
            @Value("${twilio.phoneNumber}") String phoneNumber) {

        Twilio.init(accountSid, authToken);
        Message message = Message.creator(
                new com.twilio.type.PhoneNumber(phoneNumber),
                new com.twilio.type.PhoneNumber(phoneNumber),
                "Hello from ValorGuard")
                .create();
        System.out.println(message.getBody());
    }
}