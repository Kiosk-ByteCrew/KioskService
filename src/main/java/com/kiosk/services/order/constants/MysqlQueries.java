package com.kiosk.services.order.constants;

public class MysqlQueries {

    // Insert query for orders
    public static final String INSERT_ORDER = "INSERT INTO orders (userId, restaurantId, totalAmount, orderDetailsId) VALUES (?, ?, ?, ?)";

    public static final String FETCH_ORDER_BY_ID = "SELECT * FROM orders WHERE id = ?";
    public static final String FETCH_PAST_ORDERS_BY_USER_ID = "SELECT * FROM orders WHERE userId = ?";
    public static final String FETCH_PAST_ORDERS_BY_USER_NAME = "SELECT * FROM orders WHERE userName = ?";
}
