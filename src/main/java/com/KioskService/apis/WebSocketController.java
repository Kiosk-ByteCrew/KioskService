package com.KioskService.apis;

import com.KioskService.services.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
// Not strictly necessary unless you have messages from Kiosk App to server
@Controller
public class WebSocketController {
    @Autowired
    private SessionService sessionService;

    @MessageMapping("/hello")
    @SendTo("/topic/greetings")
    public String greeting(String message) throws Exception {
        return "Hello, " + message + "!";
    }
}
