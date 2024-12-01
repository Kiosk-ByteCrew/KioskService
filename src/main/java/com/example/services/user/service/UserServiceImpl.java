//package com.example.services.restaurant.service;
//import com.example.model.User;
//import com.example.services.restaurant.constants.MysqlQueries;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.jdbc.core.BeanPropertyRowMapper;
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.stereotype.Service;
//
//import java.util.Arrays;
//import java.util.List;
//
//@Service
//public class UserServiceImpl implements UserService {
//
//    @Autowired
//    JdbcTemplate jdbcTemplate;
//
//    public User fetchUserDetails(Long userId) {
//        // Fetch user name
//        String sqlQuery = MysqlQueries.FETCH_USER_BY_USER_ID;
//
//        // Map user details from the database
//        User user = jdbcTemplate.queryForObject(
//                sqlQuery,
//                new Object[]{userId}, // Parameters for the query
//                (rs, rowNum) -> new User(
//                        rs.getLong("id"),
//                        rs.getString("userName"),
//                        null // Past orders will be set separately
//                )
//        );
//
//        // Fetch past orders (mock or actual query based on your database structure)
//        //List<String> pastOrders = fetchPastOrdersByUserId(userId);
//        //user.setPastOrders(pastOrders);
//
//        return user;
//    }
//}
package com.example.services.user.service;

import com.example.model.Order;
import com.example.model.User;
import com.example.services.user.constants.MysqlQueries;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public User fetchUserDetails(Long userId) {
        // Fetch user details
        String sqlQuery = MysqlQueries.FETCH_USER_BY_USER_ID;

        User user = jdbcTemplate.queryForObject(
                sqlQuery,
                new Object[]{userId},
                (rs, rowNum) -> new User(
                        rs.getLong("id"),
                        rs.getString("userName"),
                        null // Past orders will be set later
                )
        );

        // Fetch user's past orders
        List<Order> pastOrders = fetchOrdersByUserId(userId);
        user.setPastOrders(pastOrders);

        return user;
    }

    private List<Order> fetchOrdersByUserId(Long userId) {
        String sqlQuery = "SELECT * FROM orders WHERE userId = ?";
        return jdbcTemplate.query(
                sqlQuery,
                new Object[]{userId},
                new BeanPropertyRowMapper<>(Order.class)
        );
    }
}
