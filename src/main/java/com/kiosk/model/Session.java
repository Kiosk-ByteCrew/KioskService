package com.kiosk.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Session {
    private String sessionId;
    private User user;

    public Session() {}
    public Session(String sessionId, User user) {
        this.sessionId = sessionId;
        this.user = user;
    }
}
