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

    private Integer userId;
    private String userName;
    private Integer restaurantId;
    private List<ItemDetails> itemDetails;
    private Integer tenantId;
    private Long createdAt;
    private String status;
}
