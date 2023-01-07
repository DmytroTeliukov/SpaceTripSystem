package org.dteliukov.service;

import org.dteliukov.dao.DaoRepository;
import org.dteliukov.dao.OrderDao;
import org.dteliukov.dao.TypeDao;
import org.dteliukov.model.domain.Order;

import java.util.List;

public class OrderService {
    private final OrderDao dao;

    public OrderService() {
        dao = DaoRepository.getDao(TypeDao.MySQL).getOrderDao();
    }

    public void createOrder(Order order) {
        dao.createOrder(order);
    }

    public void updateOrderStatus(int id, String status) {
        dao.updateOrderStatus(id, status);
    }

    public void deleteOrder(int id) {
        dao.deleteOrder(id);
    }

    public List<Order> readOrders() {
        return dao.readOrders();
    }

    public void processOrder(int id) {
        dao.processOrder(id);
    }

    public Order getOrder(int id) {
        var order = dao.getOrderById(id);

        return order.orElseThrow(() -> new RuntimeException("User does not exist!"));
    }

    public List<Order> getOrdersByTrip(int id) {
        return dao.getOrdersByTrip(id);
    }
}
