package com.kiosk.services.user.repository;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kiosk.model.User;
import com.kiosk.services.user.constants.MysqlQueries;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;

@Repository
@Slf4j
public class UserRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    ObjectMapper objectMapper = new ObjectMapper();


    public User fetchUserDetails(int userId) {
        String sqlQuery = MysqlQueries.FETCH_USER_BY_USER_ID;

        return jdbcTemplate.queryForObject(
                sqlQuery,
                new Object[]{userId},
                (rs, rowNum) -> new User(
                        rs.getInt("id"),
                        rs.getString("userName"),
                        null
                )
        );
    }

    public int saveUser(User user) {
        String sqlQuery = MysqlQueries.INSERT_USER;
        KeyHolder keyHolder = new GeneratedKeyHolder();

        int rowsAffected = jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sqlQuery, PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setString(1, user.getUserName());
            try {
                ps.setString(2, objectMapper.writeValueAsString(user.getUserDetails()));
            } catch (JsonProcessingException e) {
                log.error("Failed to serialize UserDetails object to JSON", e);
                throw new RuntimeException(e);
            }
            return ps;
        }, keyHolder);

        if (rowsAffected > 0) {
            Number key = keyHolder.getKey();
            if (key != null) {
                return key.intValue();
            } else {
                throw new RuntimeException("Failed to retrieve the generated user ID.");
            }
        } else {
            throw new RuntimeException("Failed to insert the user into the database.");
        }
    }
}
