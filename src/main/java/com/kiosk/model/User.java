package com.kiosk.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class User {
    private String id;
    private String username;

    public User() {}
    public User(String id, String username) {
        this.id = id;
        this.username = username;
    }
}