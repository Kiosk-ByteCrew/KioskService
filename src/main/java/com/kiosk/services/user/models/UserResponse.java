package com.kiosk.services.user.models;

import com.kiosk.model.Order;
import com.kiosk.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {

    private int id;
    private String userName;
    private List<Order> pastOrders;

    public static UserResponse toUserResponse(User user, List<Order> pastOrders) {
        UserResponse userResponse = new UserResponse();
        userResponse.setId(user.getId());
        userResponse.setUserName(user.getUserName());
        userResponse.setPastOrders(pastOrders);

        return userResponse;
    }
}
