package com.kiosk.services.order.models;

import com.kiosk.model.Order;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderResponse {

    private int id;
    private int userId;
    private int restaurantId;
    private double totalAmount;
    private String orderDetailsId;

    public static OrderResponse getOrderResponse(Order order) {
        OrderResponse orderResponse = new OrderResponse();
        orderResponse.setId(order.getId());
        orderResponse.setUserId(order.getUserId());
        orderResponse.setRestaurantId(order.getRestaurantId());
        orderResponse.setTotalAmount(order.getTotalAmount());
        orderResponse.setOrderDetailsId(order.getOrderDetailsId());

        return orderResponse;
    }
}
