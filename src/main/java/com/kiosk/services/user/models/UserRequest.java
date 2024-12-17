package com.kiosk.services.user.models;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.kiosk.model.User;
import com.kiosk.model.UserDetails;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserRequest {

    private String username;
    private UserDetails userDetails;

    public static User toUser(UserRequest userRequest) {
        User user = new User();
        user.setUserName(userRequest.getUsername());
        user.setUserDetails(userRequest.getUserDetails());

        return user;
    }
}
