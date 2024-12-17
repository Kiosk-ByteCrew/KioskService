package com.kiosk.services.order.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.kiosk.model.ItemDetails;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class OrderRequest {

    private int userId;
    private int restaurantId;
    private List<ItemDetails> itemDetails;
    private int tenantId;
    private long createdAt;
    private String status;
}
