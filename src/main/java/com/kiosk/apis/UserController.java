package com.kiosk.apis;


import com.kiosk.model.Order;
import com.kiosk.model.User;
import com.kiosk.services.order.models.OrderResponse;
import com.kiosk.services.user.models.UserRequest;
import com.kiosk.services.user.models.UserResponse;
import com.kiosk.services.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/{userId}")
    public UserResponse fetchUserDetails(@PathVariable int userId) {
        return userService.fetchUserDetails(userId);
    }

    @PostMapping("/create")
    public User createUser(@RequestBody UserRequest userRequest) {
        return userService.createUser(userRequest);
    }

    @PostMapping("/pastOrders")
    public List<OrderResponse> pastOrders(@RequestBody User user) {
        return userService.getAllOrderForUser(user.getUserName(), user.getId());
    }
}
