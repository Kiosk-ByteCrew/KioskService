package com.kiosk.services.order.service;

import com.kiosk.model.Order;
import com.kiosk.services.order.models.OrderRequest;
import com.kiosk.services.order.models.OrderResponse;

public interface OrderService {

    OrderResponse placeOrder(OrderRequest orderRequest);

    OrderResponse fetchOrderById(int orderId);
}

