package com.kiosk.apis;

import com.kiosk.services.order.models.OrderRequest;
import com.kiosk.services.order.models.OrderResponse;
import com.kiosk.services.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/create")
    public OrderResponse placeOrder(@RequestBody OrderRequest orderRequest) {
        return orderService.placeOrder(orderRequest);
    }

    @GetMapping("/{orderId}")
    public OrderResponse fetchOrderById(@PathVariable int orderId) {
        return orderService.fetchOrderById(orderId);
    }
}
