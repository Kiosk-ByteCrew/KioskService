package com.kiosk.services.order.respository;

import com.kiosk.model.Order;
import com.kiosk.services.order.constants.MysqlQueries;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.util.List;

@Repository
public class OrderRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public int saveOrder(Order order) {
        String sqlQuery = MysqlQueries.INSERT_ORDER;
        KeyHolder keyHolder = new GeneratedKeyHolder();

        int rowsAffected = jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sqlQuery, PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setInt(1, order.getUserId());
            ps.setInt(2, order.getRestaurantId());
            ps.setDouble(3, order.getTotalAmount());
            ps.setString(4, order.getOrderDetailsId());
            return ps;
        }, keyHolder);

        if (rowsAffected > 0) {
            Number key = keyHolder.getKey();
            if (key != null) {
                return key.intValue();
            } else {
                throw new RuntimeException("Failed to retrieve the generated order ID.");
            }
        } else {
            throw new RuntimeException("Failed to insert the order into the database.");
        }
    }

    public Order findOrderById(int orderId) {
        String sqlQuery = MysqlQueries.FETCH_ORDER_BY_ID;
        return jdbcTemplate.queryForObject(sqlQuery, new BeanPropertyRowMapper<>(Order.class), orderId);
    }

    public List<Order> findOrderByUserId(int userId) {
        String sqlQuery = MysqlQueries.FETCH_PAST_ORDERS_BY_USER_ID;
        return jdbcTemplate.query(
                sqlQuery,
                new Object[]{userId},
                new BeanPropertyRowMapper<>(Order.class)
        );
    }
}
