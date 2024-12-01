package com.example.services.order.constants;

public class MysqlQueries {

    // Insert query for orders
    public static final String INSERT_ORDER = "INSERT INTO orders (id, userId, restaurantId, item, quantity, price) VALUES (?, ?, ?, ?, ?, ?)";

    public static final String FETCH_ORDER_BY_ID = "SELECT * FROM orders WHERE id = ?";
}
