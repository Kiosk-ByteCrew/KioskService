package com.KioskService.services;

import com.KioskService.model.Session;
import org.springframework.stereotype.Service;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class SessionService {
    private final ConcurrentHashMap<String, Session> sessions = new ConcurrentHashMap<>();
    public void createSession(String sessionId) {
        sessions.put(sessionId, new Session(sessionId, null));
    }
    public Session getSession(String sessionId) {
        return sessions.get(sessionId);
    }
    public ConcurrentHashMap<String, Session> getAllSession() {
        return sessions;
    }
    public void associateUserWithSession(String sessionId, Session session) {
        sessions.put(sessionId, session);
    }
    public void removeSession(String sessionId) {
        sessions.remove(sessionId);
    }
}

