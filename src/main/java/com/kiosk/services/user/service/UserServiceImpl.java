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
package com.kiosk.services.user.service;

import com.kiosk.model.Order;
import com.kiosk.model.Restaurant;
import com.kiosk.model.User;
import com.kiosk.model.UserDetails;
import com.kiosk.services.order.models.OrderResponse;
import com.kiosk.services.order.respository.OrderDetailsRepository;
import com.kiosk.services.order.respository.OrderRepository;
import com.kiosk.services.restaurant.respository.RestaurantRepository;
import com.kiosk.services.user.models.UserRequest;
import com.kiosk.services.user.models.UserResponse;
import com.kiosk.services.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderDetailsRepository orderDetailsRepository;
    @Autowired
    private RestaurantRepository restaurantRepository;

    public UserResponse fetchUserDetails(int userId) {
        User user = userRepository.fetchUserDetails(userId);
        List<Order> pastOrders = fetchOrdersByUserId(userId);
        return UserResponse.toUserResponse(user, pastOrders);
    }

    @Override
    public User createUser(UserRequest userRequest) {
        validateUserRequest(userRequest);
        User user = UserRequest.toUser(userRequest);

        int userId = userRepository.saveUser(user);
        user.setId(userId);

        return user;
    }

    @Override
    public User findOrCreateUser(String username, String email) {
        User user = fetchUserByUserName(username);
        if (user != null) {
            return user;
        }
        UserRequest userRequest = new UserRequest();
        userRequest.setUsername(username);
        UserDetails userDetails = new UserDetails();
        userDetails.setNotifications(true);
        userDetails.setTheme("System");
        userDetails.setEmail(email);

        userRequest.setUserDetails(userDetails);
        user = createUser(userRequest);

        return user;
    }

    @Override
    public List<OrderResponse> getAllOrderForUser(String userName, Integer id) {
        List<Order> orders = new ArrayList<>();
        if(id == null) {
            Integer userId = userRepository.fetchUserByName(userName).getId();
            orders = fetchOrdersByUserId(userId);
        } else {
            orders = fetchOrdersByUserId(id);
        }
        List<Restaurant> restaurants = restaurantRepository.fetchAllRestaurants();
        List<OrderResponse> orderResponses = new ArrayList<>();
        for(Order order : orders) {
            String restaurantName = null;
            for(Restaurant restaurant : restaurants) {
                if(restaurant.getId() == order.getRestaurantId()) {
                    restaurantName = restaurant.getName();
                }
            }
            orderResponses.add(OrderResponse.getOrderResponse(order, restaurantName, orderDetailsRepository.findById(order.getOrderDetailsId())));
        }
        return orderResponses;
    }

    private User fetchUserByUserName(String username) {
        return userRepository.fetchUserByName(username);
    }

    private void validateUserRequest(UserRequest userRequest) {
        if (userRequest == null) {
            throw new IllegalArgumentException("UserRequest cannot be null");
        }

        if(!StringUtils.hasLength(userRequest.getUsername())){
            throw new IllegalArgumentException("Username cannot be empty or null");
        }
        if(userRequest.getUserDetails() == null) {
            throw new IllegalArgumentException("User Details are empty, can't proceed.");
        }
    }

    private List<Order> fetchOrdersByUserId(int userId) {
       return orderRepository.findOrderByUserId(userId);
    }
}
