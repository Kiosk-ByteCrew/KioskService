package com.kiosk.services.restaurant.service;

import com.kiosk.model.Restaurant;

import java.util.List;

public interface RestaurantService {

    List<Restaurant> fetchAllRestaurants();
}
