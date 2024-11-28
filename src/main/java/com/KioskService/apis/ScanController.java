package com.KioskService.apis;

import com.KioskService.model.Session;
import com.KioskService.model.User;
import com.KioskService.services.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
//@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class ScanController {

    @Autowired
    private SessionService sessionService;

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    /**
     * Existing method to create a new session.
     */
    @PostMapping("/session")
    public ResponseEntity<String> createSession(@RequestBody Map<String, String> payload) {
        String sessionId = payload.get("sessionId");
        if (sessionId == null || sessionId.isEmpty()) {
            return ResponseEntity.badRequest().body("Session ID is required");
        }
        sessionService.createSession(sessionId);
        return ResponseEntity.ok("Session created successfully");
    }

    /**
     * Existing test endpoint.
     */
    @GetMapping("/test")
    public String test() {
        return "test.";
    }

    /**
     * Existing method to handle QR code scans and associate users with sessions.
     */
    @PostMapping("/scan")
    public ResponseEntity<String> scanQrCode(@RequestBody Map<String, String> payload) {
        String sessionId = payload.get("sessionId");
        String username = payload.get("username");
        if (sessionId == null || sessionId.isEmpty()) {
            return ResponseEntity.badRequest().body("Session ID is required");
        }
        if (username == null || username.isEmpty()) {
            return ResponseEntity.badRequest().body("Username is required");
        }
        Session session = sessionService.getSession(sessionId);
        if (session == null) {
            return ResponseEntity.status(404).body("Session not found");
        }

        // Associate user with session
        User user = new User();
        user.setUsername(username);
        session.setUser(user);
        sessionService.associateUserWithSession(sessionId, session);

        // Notify Kiosk App via WebSocket
        messagingTemplate.convertAndSend("/session/" + sessionId, username);
        return ResponseEntity.ok("Scan successful");
    }

    /**
     * New method to retrieve the status of a session.
     */
    @GetMapping("/session/{sessionId}/status")
    public ResponseEntity<?> getSessionStatus(@PathVariable String sessionId) {
        Session session = sessionService.getSession(sessionId);

        if (session == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Session not found");
        }

        // Determine the session status based on whether a user is associated
        boolean isConnected = session.getUser() != null;

        // Prepare the response data
        Map<String, Object> response = new HashMap<>();
        response.put("status", isConnected ? "connected" : "pending");

        if (isConnected) {
            response.put("user", session.getUser().getUsername());
        }

        return ResponseEntity.ok(response);
    }
}
