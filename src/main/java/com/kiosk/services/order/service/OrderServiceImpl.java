package com.kiosk.services.order.service;

import com.kiosk.model.ItemDetails;
import com.kiosk.model.Order;
import com.kiosk.model.OrderDetails;
import com.kiosk.services.order.models.OrderRequest;
import com.kiosk.services.order.models.OrderResponse;
import com.kiosk.services.order.respository.OrderDetailsRepository;
import com.kiosk.services.order.respository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderDetailsRepository orderDetailsRepository;

    @Override
    public OrderResponse placeOrder(OrderRequest orderRequest) {

        OrderDetails orderDetails = new OrderDetails();
        orderDetails.setItemDetailsList(orderRequest.getItemDetails());
        orderDetails = orderDetailsRepository.save(orderDetails);

        Order order = new Order();
        order.setOrderDetailsId(orderDetails.getId());
        order.setUserId(orderRequest.getUserId());
        order.setRestaurantId(orderRequest.getRestaurantId());
        order.setTotalAmount(getTotalAmount(orderDetails.getItemDetailsList()));
        int orderId = orderRepository.saveOrder(order);
        order.setId(orderId);

        return OrderResponse.getOrderResponse(order);
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
        return OrderResponse.getOrderResponse(orderRepository.findOrderById(orderId));
    }
}

