package com.example.apis;

import com.example.model.User;
import com.example.services.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/{userId}")
    public User fetchUserDetails(@PathVariable Long userId) {
        return userService.fetchUserDetails(userId);
    }
}
