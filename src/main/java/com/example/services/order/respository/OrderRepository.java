package com.example.services.order.respository;

import com.example.model.Order;
import com.example.services.order.constants.MysqlQueries;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class OrderRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    // Save a new order
    public void saveOrder(Order order) {
        String sqlQuery = MysqlQueries.INSERT_ORDER;
        jdbcTemplate.update(
                sqlQuery,
                order.getId(),
                order.getUserId(),
                order.getRestaurantId(),
                order.getTotalAmount(),
                order.getOrderDetailsId()
        );
    }

    // Fetch an order by ID
    public Order findOrderById(Long orderId) {
        String sqlQuery = MysqlQueries.FETCH_ORDER_BY_ID;
        return jdbcTemplate.queryForObject(sqlQuery, new BeanPropertyRowMapper<>(Order.class), orderId);
    }
}
