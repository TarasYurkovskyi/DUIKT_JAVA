package org.pz1.domain;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class User {
    private int id;
    private String name;
    private String email;
    private List<Order> orderHistory;

    public User(int id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.orderHistory = new ArrayList<>();
    }

    public void addOrder(Order order) {
        if (order != null) {
            order.setUserId(this.id);
            this.orderHistory.add(order);
        }
    }

    public double getTotalSpent() {
        return orderHistory.stream()
                .mapToDouble(Order::getTotalPrice)
                .sum();
    }

    public int getTotalOrders() {
        return orderHistory.size();
    }
}
