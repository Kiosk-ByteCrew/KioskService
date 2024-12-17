package com.kiosk.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemDetails {

    private int itemId;
    private String itemName;
    private int quantity;
    private double price;
}
