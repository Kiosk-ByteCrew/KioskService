package com.example.services.user.service;

import com.example.model.User;

public interface UserService {
    // Fetch user details based on user ID
    User fetchUserDetails(Long userId);
}
