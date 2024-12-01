package com.example.services.order.service;

import com.example.model.Order;

public interface OrderService {

    Order placeOrder(Order order);
    Order fetchOrderById(Long orderId);
}

