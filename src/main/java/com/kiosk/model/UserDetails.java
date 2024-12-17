package com.kiosk.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDetails {

    private String firstName;
    private String lastName;
    private String email;
    private Long contactNumber;
    private boolean notifications;
    private String theme;
}
