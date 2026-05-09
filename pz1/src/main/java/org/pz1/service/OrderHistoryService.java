package org.pz1.service;

import org.pz1.domain.Order;
import org.pz1.domain.User;
import org.pz1.domain.Cart;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderHistoryService {
    private final Map<Integer, User> users;
    private int nextOrderId = 1;

    public OrderHistoryService() {
        this.users = new HashMap<>();
    }

    public void registerUser(User user) {
        if (user != null && !users.containsKey(user.getId())) {
            users.put(user.getId(), user);
        }
    }

    public Order createOrder(int userId, Cart cart) {
        User user = users.get(userId);
        if (user == null || cart == null || cart.getProducts().isEmpty()) {
            return null;
        }

        Order order = new Order(cart);
        order.setId(nextOrderId++);
        order.setUserId(userId);
        user.addOrder(order);
        return order;
    }

    public List<Order> getUserOrderHistory(int userId) {
        User user = users.get(userId);
        if (user == null) {
            return new ArrayList<>();
        }
        return user.getOrderHistory();
    }

    public Order getOrder(int userId, int orderId) {
        User user = users.get(userId);
        if (user == null) {
            return null;
        }
        return user.getOrderHistory().stream()
                .filter(o -> o.getId() == orderId)
                .findFirst()
                .orElse(null);
    }

    public void updateOrderStatus(int userId, int orderId, String newStatus) {
        Order order = getOrder(userId, orderId);
        if (order != null) {
            order.setStatus(newStatus);
        }
    }

    public double getUserTotalSpent(int userId) {
        User user = users.get(userId);
        if (user == null) {
            return 0;
        }
        return user.getTotalSpent();
    }

    public int getUserTotalOrders(int userId) {
        User user = users.get(userId);
        if (user == null) {
            return 0;
        }
        return user.getTotalOrders();
    }

    public List<Order> getOrdersByStatus(int userId, String status) {
        User user = users.get(userId);
        if (user == null) {
            return new ArrayList<>();
        }
        return user.getOrderHistory().stream()
                .filter(o -> o.getStatus().equals(status))
                .toList();
    }
}
