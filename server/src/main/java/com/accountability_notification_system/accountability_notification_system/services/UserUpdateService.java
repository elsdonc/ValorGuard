package com.accountability_notification_system.accountability_notification_system.services;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import com.accountability_notification_system.accountability_notification_system.exceptions.UserNotFoundException;
import com.accountability_notification_system.accountability_notification_system.model.User;
import com.accountability_notification_system.accountability_notification_system.repositories.UserRepository;

@Service
public class UserUpdateService {

    @Autowired
    private UserRepository userRepository;

    public void saveUserData(OAuth2User oAuth2User, Map<String, String> json) throws Exception, UserNotFoundException {
        User user = userRepository.findByEmail(oAuth2User.getAttribute("email"));
        if (user == null) {
            throw new Exception("User not found");
        }
        try {
            updateUserData(user, json);
            userRepository.save(user);
        } catch (UserNotFoundException e) {
            throw e;
        }
    }

    private void updateUserData(User user, Map<String, String> json) throws UserNotFoundException {
        System.out.println("Updating user data for: " + user.getEmail());
        if (user.getValUser() == null || !user.getValUser().equals(json.get("valUser"))) {
            user.setValUser(json.get("valUser"));
        }
        if (user.getValTag() == null || !user.getValTag().equals(json.get("valTag"))) {
            user.setValTag(json.get("valTag"));
        }
        if (user.getNumber() == null || !user.getNumber().equals(json.get("number"))) {
            user.setNumber(json.get("number"));
        }
        if (user.getAccPartnerNumber() == null || !user.getAccPartnerNumber().equals(json.get("accPartnerNumber"))) {
            user.setAccPartnerNumber(json.get("accPartnerNumber"));
        }
        try {
            ValorantMatchHistoryScraperService.checkHistory(user);
        } catch (UserNotFoundException e) {
            throw e;
        }
    }
}
