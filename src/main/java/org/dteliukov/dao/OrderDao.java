package org.dteliukov.dao;

import org.dteliukov.model.domain.Order;

import java.util.List;
import java.util.Optional;

public interface OrderDao {
    void createOrder(Order order);
    void updateOrderStatus(int id, String status);
    void processOrder(int id);
    List<Order> readOrders();
    void deleteOrder(int id);
    List<Order> getOrdersByTrip(int idTrip);
    List<Order> getOrdersByUser(String email);
    Optional<Order> getOrderById(int id);
}
