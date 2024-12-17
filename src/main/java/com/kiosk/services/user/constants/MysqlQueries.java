package com.kiosk.services.user.constants;

public class MysqlQueries {

    public static final String FETCH_USER_BY_USER_ID = "SELECT * FROM users WHERE id = ?";
    public static final String INSERT_USER = "INSERT INTO users (userName, userDetails) VALUES (?, ?)";
    public static final String FETCH_USER_BY_USER_NAME = "SELECT * FROM users WHERE userName = ?";
}
