package com.accountability_notification_system.accountability_notification_system.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String email;

    private String name;

    private String val_user;
    
    private String val_tag;

    private Integer match_count;

    private String number;

    private String acc_partner_number;

    public User() {
        this.match_count = 0;
    }

    public Integer getId() {
        return this.id;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String new_email) {
        this.email = new_email;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String new_name) {
        this.name = new_name;
    }

    public String getValUser() {
        return this.val_user;
    }

    public void setValUser(String new_val_user) {
        this.val_user = new_val_user;
    }

    public String getValTag() {
        return this.val_tag;
    }

    public void setValTag(String new_val_tag) {
        this.val_tag = new_val_tag;
    }

    public Integer getMatchCount() {
        return this.match_count;
    }

    public void setMatchCount(Integer new_match_count) {
        this.match_count = new_match_count;
    }

    public String getNumber() {
        return this.number;
    }

    public void setNumber(String new_number) {
        this.number = new_number;
    }

    public String getAccPartnerNumber() {
        return acc_partner_number;
    }

    public void setAccPartnerNumber(String new_acc_partner_number) {
        this.acc_partner_number = new_acc_partner_number;
    }

}
