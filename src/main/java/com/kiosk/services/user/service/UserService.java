package com.kiosk.services.user.service;

import com.kiosk.model.User;
import com.kiosk.services.user.models.UserRequest;
import com.kiosk.services.user.models.UserResponse;

public interface UserService {

    UserResponse fetchUserDetails(int userId);
    User createUser(UserRequest userRequest);

    User findOrCreateUser(String username, String email);
}
