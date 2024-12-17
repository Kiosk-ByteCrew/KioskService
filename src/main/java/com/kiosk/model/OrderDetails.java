package com.kiosk.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "OrderDetails")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetails {
    @Id
    private String id;
    private List<ItemDetails> itemDetailsList;
}
