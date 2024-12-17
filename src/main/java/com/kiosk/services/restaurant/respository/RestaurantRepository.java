package com.kiosk.services.restaurant.respository;

import com.kiosk.model.Restaurant;
import com.kiosk.services.restaurant.constants.MysqlQueries;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RestaurantRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public List<Restaurant> fetchAllRestaurants(){
        String sqlQuery = MysqlQueries.FETCH_ALL_RESTAURANTS;
        return jdbcTemplate.query(sqlQuery, new BeanPropertyRowMapper<>(Restaurant.class));
    }
}
