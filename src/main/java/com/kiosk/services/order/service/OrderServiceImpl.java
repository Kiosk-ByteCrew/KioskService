package com.kiosk.services.order.service;

import com.kiosk.model.*;
import com.kiosk.services.order.models.OrderRequest;
import com.kiosk.services.order.models.OrderResponse;
import com.kiosk.services.order.respository.OrderDetailsRepository;
import com.kiosk.services.order.respository.OrderRepository;
import com.kiosk.services.restaurant.respository.RestaurantRepository;
import com.kiosk.services.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderDetailsRepository orderDetailsRepository;
    @Autowired
    private RestaurantRepository restaurantRepository;
    @Autowired
    private UserRepository userRepository;

    @Override
    public OrderResponse placeOrder(OrderRequest orderRequest) {
        User user = userRepository.fetchUserByName(orderRequest.getUserName());
        if(user == null) {
            throw new Error("Invalid user name");
        }

        OrderDetails orderDetails = new OrderDetails();
        orderDetails.setItemDetailsList(orderRequest.getItemDetails());
        orderDetails = orderDetailsRepository.save(orderDetails);

        Order order = new Order();
        order.setOrderDetailsId(orderDetails.getId());
        order.setUserId(user.getId());
        order.setRestaurantId(orderRequest.getRestaurantId());
        order.setTotalAmount(getTotalAmount(orderDetails.getItemDetailsList()));
        int orderId = orderRepository.saveOrder(order);
        order.setId(orderId);

        List<Restaurant> restaurants = restaurantRepository.fetchAllRestaurants();
        String restaurantName = null;
        for(Restaurant restaurant : restaurants) {
            if(restaurant.getId() == order.getRestaurantId()) {
                restaurantName = restaurant.getName();
            }
        }
        return OrderResponse.getOrderResponse(order, restaurantName, orderDetails);
    }

    private double getTotalAmount(List<ItemDetails> itemDetailsList) {
        double totalAmount = 0;
        for (ItemDetails itemDetails : itemDetailsList) {
            totalAmount += itemDetails.getPrice() * itemDetails.getQuantity();
        }
        return totalAmount;
    }

    @Override
    public OrderResponse fetchOrderById(int orderId) {
        Order order = orderRepository.findOrderById(orderId);
        OrderDetails orderDetails = orderDetailsRepository.findById(order.getOrderDetailsId());
        List<Restaurant> restaurants = restaurantRepository.fetchAllRestaurants();
        String restaurantName = null;
        for(Restaurant restaurant : restaurants) {
            if(restaurant.getId() == order.getRestaurantId()) {
                restaurantName = restaurant.getName();
            }
        }
        return OrderResponse.getOrderResponse(order, restaurantName, orderDetails);
    }
}

