package com.kiosk.services.order.respository;

import com.kiosk.model.OrderDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class OrderDetailsRepository {

    @Autowired
    private MongoTemplate mongoTemplate;

    private static final String ORDER_DETAILS_COLLECTION_NAME = "OrderDetails";


    public OrderDetails save(OrderDetails orderDetails) {
        return mongoTemplate.insert(orderDetails, ORDER_DETAILS_COLLECTION_NAME);
    }

    public List<OrderDetails> findAll() {
        return mongoTemplate.findAll(OrderDetails.class);
    }
}
