package com.kiosk.services.restaurant.service;

import com.kiosk.model.Restaurant;
import com.kiosk.services.restaurant.respository.RestaurantRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class RestaurantServiceImpl implements RestaurantService {

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Override
    public List<Restaurant> fetchAllRestaurants() {
        return restaurantRepository.fetchAllRestaurants();
    }
}
