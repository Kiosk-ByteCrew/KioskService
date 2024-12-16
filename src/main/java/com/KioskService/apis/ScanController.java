package com.KioskService.apis;

import com.KioskService.model.Session;
import com.KioskService.model.User;
import com.KioskService.services.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@RestController
@CrossOrigin(origins = "*")
public class ScanController {

    @Autowired
    private SessionService sessionService;

    @Value("${api.key}")
    private String VALID_API_KEY;

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

    @GetMapping("/session/all")
    public ResponseEntity<?> getAllSessions(@RequestHeader(value = "API-Key", required = false) String apiKey) {
        // Check if the API key is provided and valid
        if (apiKey == null || !apiKey.equals(VALID_API_KEY)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Forbidden: Invalid or missing API key");
        }

        // Fetch all sessions from the session service
        ConcurrentHashMap<String, Session> sessions = sessionService.getAllSession();

        // If no sessions exist, return a 404 NOT FOUND status
        if (sessions.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No sessions found");
        }

        // Prepare a list to store the session details to return as a response
        List<Map<String, Object>> response = new ArrayList<>();

        // Loop through each session and build a response for each session
        for (Map.Entry<String, Session> entry : sessions.entrySet()) {
            String sessionId = entry.getKey();
            Session session = entry.getValue();

            // Determine the session status: connected or pending
            boolean isConnected = session.getUser() != null;

            // Prepare a map for each session's details
            Map<String, Object> sessionDetails = new HashMap<>();
            sessionDetails.put("sessionId", sessionId);
            sessionDetails.put("status", isConnected ? "connected" : "pending");

            // If the session is connected, include user details
            if (isConnected) {
                sessionDetails.put("user", session.getUser().getUsername());
            }

            // Add the session details to the response list
            response.add(sessionDetails);
        }

        // Return the list of sessions with HTTP status 200 OK
        return ResponseEntity.ok(response);
    }

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
