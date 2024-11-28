package com.KioskService.apis;


import com.KioskService.model.Restaurant;
import com.KioskService.services.restaurant.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping()
public class KioskController {

    @Autowired
    private RestaurantService restaurantService;

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
}
