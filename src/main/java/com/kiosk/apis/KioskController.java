package com.kiosk.apis;

import com.kiosk.model.Restaurant;
import com.kiosk.model.User;
import com.kiosk.services.restaurant.service.RestaurantService;
import com.kiosk.services.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping()
public class KioskController {

    @Autowired
    private RestaurantService restaurantService;
    @Autowired
    private UserService userService;

    @GetMapping("/try")
    public String hello() {
        return "hello.";
    }

    @GetMapping("/fetch/restaurants")
    public List<Restaurant> fetchRestaurants() {
        if (restaurantService.fetchAllRestaurants().isEmpty()){
            System.out.println("No restaurants found");
        }
        return restaurantService.fetchAllRestaurants();
    }
//
//    @PostMapping("/test")
//    public User testUser(@RequestBody Map<String, String> test) {
//        System.out.println(test);
//        return userService.findOrCreateUser(test.get("userName"), test.get("email"));
//    }
}
