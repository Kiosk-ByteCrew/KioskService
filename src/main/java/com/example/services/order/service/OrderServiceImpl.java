package com.example.services.order.service;

import com.example.model.Order;
import com.example.services.order.constants.MysqlQueries;
import com.example.services.order.respository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

//    @Autowired
//    private OrderRepository orderRepository;
//
//    @Override
//    public Order placeOrder(Order order) {
//        // Save the order
//        orderRepository.saveOrder(order);
//        // Return the saved order (if needed, fetch it back from DB for consistency)
//        return order;
//    }
//
//    @Override
//    public Order fetchOrderById(Long orderId) {
//        // Fetch order by ID
//        return orderRepository.findOrderById(orderId);
//    }
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public Order placeOrder(Order order) {
        // Save the order
        orderRepository.saveOrder(order);
        // Return the saved order
        return order;
    }

    @Override
    public Order fetchOrderById(Long orderId) {
        // Fetch order by ID
        return orderRepository.findOrderById(orderId);
    }
}

