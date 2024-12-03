package com.example.apis;

import com.example.model.Order;
import com.example.services.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/create")
    public Order placeOrder(@RequestBody Order order) {
        return orderService.placeOrder(order);
    }

    @GetMapping("/{orderId}")
    public Order fetchOrderById(@PathVariable Long orderId) {
        System.out.println("Api found");
        return orderService.fetchOrderById(orderId);
    }
}
