package com.kiosk.services.order.models;

import com.kiosk.model.Order;
import com.kiosk.model.OrderDetails;
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
    private String restaurantName;
    private double totalAmount;
    private String orderDetailsId;
    private OrderDetails orderDetails;

    public static OrderResponse getOrderResponse(Order order, String restaurantName, OrderDetails orderDetails) {
        OrderResponse orderResponse = new OrderResponse();
        orderResponse.setId(order.getId());
        orderResponse.setUserId(order.getUserId());
        orderResponse.setRestaurantId(order.getRestaurantId());
        orderResponse.setTotalAmount(order.getTotalAmount());
        orderResponse.setOrderDetailsId(order.getOrderDetailsId());
        orderResponse.setOrderDetails(orderDetails);
        orderResponse.setRestaurantName(restaurantName);

        return orderResponse;
    }
}
