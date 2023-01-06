package org.dteliukov.dao.mysql;

import org.dteliukov.dao.*;
import org.dteliukov.model.domain.Order;
import org.dteliukov.model.domain.TripInfo;
import org.dteliukov.model.domain.UserProfile;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


class OrderMySqlDaoTest {
    static UserProfile client;
    static UserProfile operator;
    static TripInfo tripInfo;
    static UserDao userDao;
    static TripDao tripDao;
    static OrderDao orderDao;
    static Order order;
    static DateTimeFormatter pattern = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @BeforeAll
    static void setUp() {
        userDao = DaoRepository.getDao(TypeDao.MySQL).getUserDao();
        tripDao = DaoRepository.getDao(TypeDao.MySQL).getTripDao();
        orderDao = DaoRepository.getDao(TypeDao.MySQL).getOrderDao();
        client = new UserProfile("Lastname", "Firstname", "client@gmail.com",
                "0987654321", "1", "CLIENT", "ACTIVE");
        operator = new UserProfile("Lastname", "Firstname", "operator@gmail.com",
                "0987654322", "1", "OPERATOR", "ACTIVE");
        userDao.registration(client);
        userDao.registration(operator);

        tripInfo = new TripInfo(1, operator, LocalDateTime.now().format(pattern),
                LocalDateTime.now().plusDays(2).format(pattern), 160.50f, 10,
                "MERCURY", "ACTIVE");
        tripDao.createTrip(tripInfo);
        var trips = tripDao.readTrips();
        tripInfo.id(trips.get(trips.size()-1).getId());

        order = new Order(1, client, tripInfo, null, 1, 160.50f, "ORDERED");
        orderDao.createOrder(order);
        var orders = orderDao.getOrdersByTrip(tripInfo.getId());
        order.id(orders.get(orders.size()-1).getId());
    }

    @Test
    void updateOrderStatus() {
        orderDao.updateOrderStatus(order.getId(), "ACCEPTED");

        var updatedOrder = orderDao.getOrderById(order.getId()).get();

        Assertions.assertNotEquals(order.getStatus(), updatedOrder.getStatus());
    }

    @Test
    void processOrder() {
        orderDao.processOrder(order.getId());

        var updatedOrder = orderDao.getOrderById(order.getId()).get();

        Assertions.assertNotNull(updatedOrder.getProcessed());
    }

    @Test
    void readOrders() {
        var orders = orderDao.readOrders();

        Assertions.assertNotEquals(0, orders.size());

    }

    @AfterAll
    static void deleteAll() {
        orderDao.deleteOrder(order.getId());
        tripDao.deleteTrip(tripInfo.getId());
        userDao.deleteUser(operator.getEmail());
        userDao.deleteUser(client.getEmail());
    }
}