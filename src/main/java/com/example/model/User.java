//package com.example.model;
//
//import java.util.List;
//
//public class User {
//    private Long id;
//    private String name;
//    private List<String> pastOrders;
//
//    // Constructors
//    public User() {}
//
//    public User(Long id, String name, List<String> pastOrders) {
//        this.id = id;
//        this.name = name;
//        this.pastOrders = pastOrders;
//    }
//
//    // Getters and setters
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public List<String> getPastOrders() {
//        return pastOrders;
//    }
//
//    public void setPastOrders(List<String> pastOrders) {
//        this.pastOrders = pastOrders;
//    }
//}
package com.example.model;

import java.util.List;

public class User {
    private Long id;
    private String name;
    private List<Order> pastOrders; // List of orders associated with the user

    // Constructors
    public User() {}

    public User(Long id, String name, List<Order> pastOrders) {
        this.id = id;
        this.name = name;
        this.pastOrders = pastOrders;
    }

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Order> getPastOrders() {
        return pastOrders;
    }

    public void setPastOrders(List<Order> pastOrders) {
        this.pastOrders = pastOrders;
    }
}
